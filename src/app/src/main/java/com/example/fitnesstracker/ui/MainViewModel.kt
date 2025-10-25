package com.example.fitnesstracker.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.*
import com.example.fitnesstracker.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

enum class MainScreenTab { ACTIVITIES, FOOD }
data class ChartData(val date: Date, val caloriesBurned: Int, val caloriesConsumed: Int)

@OptIn(ExperimentalCoroutinesApi::class) // Opt-in for flatMapLatest
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val activityDao = db.activityDao()
    private val foodDao = db.foodDao()
    private val activityTypeDao = db.activityTypeDao()
    private val foodTypeDao = db.foodTypeDao()
    private val userSettingsRepo = UserSettingsRepository(application)

    private val _selectedDate = MutableStateFlow(getStartOfToday())
    private val _selectedTab = MutableStateFlow(MainScreenTab.ACTIVITIES)

    // --- Flows from Database & DataStore ---
    private val activitiesForDay: Flow<List<ActivityRecord>> = _selectedDate.flatMapLatest { activityDao.getActivitiesForDate(it) }
    private val foodForDay: Flow<List<FoodRecord>> = _selectedDate.flatMapLatest { foodDao.getFoodForDate(it) }
    val activityTypes: StateFlow<List<ActivityType>> = activityTypeDao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val foodTypes: StateFlow<List<FoodType>> = foodTypeDao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private val userSettings: Flow<UserSettings> = userSettingsRepo.userSettings

    // --- UI State ---
    private val _showCreateActivityDialog = MutableStateFlow(false)
    private val _activityTypeToDelete = MutableStateFlow<ActivityType?>(null)
    private val _showCreateFoodDialog = MutableStateFlow(false)
    private val _foodTypeToDelete = MutableStateFlow<FoodType?>(null)

    private val dialogState = combine(_showCreateActivityDialog, _activityTypeToDelete, _showCreateFoodDialog, _foodTypeToDelete) { showCreateAct, toDeleteAct, showCreateFood, toDeleteFood -> DialogState(showCreateAct, toDeleteAct, showCreateFood, toDeleteFood) }

    private val mainState = combine(_selectedDate, activitiesForDay, foodForDay, dialogState, userSettings) { date, acts, foods, dialogs, settings ->
        MainUiState(
            user = settings, 
            selectedDate = date, 
            activitiesForSelectedDate = acts, 
            foodForSelectedDate = foods, 
            totalCaloriesConsumed = foods.sumOf { it.calories }, 
            showCreateActivityDialog = dialogs.showCreateActivityDialog, 
            activityTypeToDelete = dialogs.activityTypeToDelete, 
            showCreateFoodDialog = dialogs.showCreateFoodDialog, 
            foodTypeToDelete = dialogs.foodTypeToDelete,
            selectedTab = MainScreenTab.ACTIVITIES // Placeholder, will be updated
        )
    }

    val uiState: StateFlow<MainUiState> = combine(mainState, _selectedTab) { main, tab ->
        main.copy(selectedTab = tab)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainUiState())

    // --- Chart Data Flow ---
    val chartData: StateFlow<List<ChartData>> = _selectedDate.flatMapLatest { date ->
        val endDate = getEndOfDay(date)
        val startDate = getDaysAgo(29, from = date)
        val activitiesInRange = activityDao.getActivitiesBetween(startDate, endDate)
        val foodInRange = foodDao.getFoodBetween(startDate, endDate)
        combine(activitiesInRange, foodInRange, userSettings) { activities, foods, settings ->
            val activityMap = activities.groupBy { getStartOfDay(it.date) }
            val foodMap = foods.groupBy { getStartOfDay(it.date) }
            val datesWithData = (activityMap.keys + foodMap.keys).distinct().sorted()
            datesWithData.map { day ->
                val burnedFromActivity = activityMap[day]?.sumOf { it.caloriesBurned } ?: 0
                val totalBurned = settings.calculateBmr() + burnedFromActivity
                val consumed = foodMap[day]?.sumOf { it.calories } ?: 0
                ChartData(day, totalBurned, consumed)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    
    // --- Init Defaults ---
    private fun addDefaultActivityTypes() = viewModelScope.launch { if (activityTypes.first().isEmpty()) { activityTypeDao.insert(ActivityType(name = "Wandern", metValue = 3.5)); activityTypeDao.insert(ActivityType(name = "Joggen", metValue = 7.0)); activityTypeDao.insert(ActivityType(name = "Fahrradfahren", metValue = 6.0)); activityTypeDao.insert(ActivityType(name = "Schwimmen", metValue = 8.0)) } }
    private fun addDefaultFoodTypes() = viewModelScope.launch { if (foodTypes.first().isEmpty()) { foodTypeDao.insert(FoodType(name = "Apfel", caloriesMediumPortion = 95)); foodTypeDao.insert(FoodType(name = "Banane", caloriesMediumPortion = 105)); foodTypeDao.insert(FoodType(name = "Pizza, 1 Stück", caloriesMediumPortion = 285)); foodTypeDao.insert(FoodType(name = "Salat", caloriesMediumPortion = 150)) } }

    init {
        addDefaultActivityTypes()
        addDefaultFoodTypes()
    }

    // --- Tab Selection ---
    fun onTabSelected(tab: MainScreenTab) { _selectedTab.value = tab }

    // --- User Settings Functions ---
    fun saveUserSettings(userSettings: UserSettings) { viewModelScope.launch { userSettingsRepo.saveUserSettings(userSettings) } }

    // --- Activity & Food Functions ---
    fun addActivity(name: String, durationMinutes: Int, intensity: Intensity) = viewModelScope.launch { val activityType = activityTypes.value.find { it.name == name } ?: return@launch; val weight = uiState.value.user.weight; val caloriesBurned = calculateCaloriesBurned(activityType.metValue, durationMinutes, intensity, weight); activityDao.insert(ActivityRecord(name = name, durationMinutes = durationMinutes, intensity = intensity, caloriesBurned = caloriesBurned, date = _selectedDate.value)) }
    fun deleteActivity(activityId: Long) = viewModelScope.launch { activityDao.delete(activityId) }
    fun addFood(name: String, portionSize: PortionSize) = viewModelScope.launch { val foodType = foodTypes.value.find { it.name == name } ?: return@launch; val calories = calculateCalories(foodType.caloriesMediumPortion, portionSize); foodDao.insert(FoodRecord(name = name, portionSize = portionSize, calories = calories, date = _selectedDate.value)) }
    fun deleteFood(foodId: Long) = viewModelScope.launch { foodDao.delete(foodId) }

    // --- Dialog Functions ---
    fun createActivityType(name: String, metValue: Double) { viewModelScope.launch { activityTypeDao.insert(ActivityType(name = name, metValue = metValue)) }; _showCreateActivityDialog.value = false }
    fun onShowCreateActivityDialog() { _showCreateActivityDialog.value = true }
    fun onDismissCreateActivityDialog() { _showCreateActivityDialog.value = false }
    fun onStartDeleteActivityType(activityType: ActivityType) { _activityTypeToDelete.value = activityType }
    fun onConfirmDeleteActivityType() = viewModelScope.launch { _activityTypeToDelete.value?.let { activityTypeDao.delete(it) }; _activityTypeToDelete.value = null }
    fun onDismissDeleteActivityType() { _activityTypeToDelete.value = null }
    fun createFoodType(name: String, calories: Int) { viewModelScope.launch { foodTypeDao.insert(FoodType(name = name, caloriesMediumPortion = calories)) }; _showCreateFoodDialog.value = false }
    fun onShowCreateFoodDialog() { _showCreateFoodDialog.value = true }
    fun onDismissCreateFoodDialog() { _showCreateFoodDialog.value = false }
    fun onStartDeleteFoodType(foodType: FoodType) { _foodTypeToDelete.value = foodType }
    fun onConfirmDeleteFoodType() = viewModelScope.launch { _foodTypeToDelete.value?.let { foodTypeDao.delete(it) }; _foodTypeToDelete.value = null }
    fun onDismissDeleteFoodType() { _foodTypeToDelete.value = null }
    
    // --- Date Functions ---
    fun selectPreviousDay() { changeDateBy(-1) }
    fun selectNextDay() { if (!isToday(_selectedDate.value)) { changeDateBy(1) } }
    private fun changeDateBy(days: Int) { val calendar = Calendar.getInstance().apply { time = _selectedDate.value }; calendar.add(Calendar.DAY_OF_YEAR, days); _selectedDate.value = calendar.time }

    // --- Utility Functions ---
    private fun calculateCaloriesBurned(metValue: Double, durationMinutes: Int, intensity: Intensity, weightKg: Double): Int { val intensityMultiplier = when (intensity) { Intensity.LOW -> 0.9; Intensity.MEDIUM -> 1.0; Intensity.HIGH -> 1.1 }; return ((metValue * intensityMultiplier * weightKg * 3.5) / 200 * durationMinutes).toInt() }
    private fun calculateCalories(baseCalories: Int, portionSize: PortionSize): Int { return when (portionSize) { PortionSize.SMALL -> (baseCalories * 0.9).toInt(); PortionSize.MEDIUM -> baseCalories; PortionSize.LARGE -> (baseCalories * 1.1).toInt() } }
    private fun getStartOfDay(date: Date): Date = Calendar.getInstance().apply { time = date; set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0) }.time
    private fun getStartOfToday(): Date = getStartOfDay(Date())
    private fun getEndOfDay(date: Date): Date = Calendar.getInstance().apply { time = date; set(Calendar.HOUR_OF_DAY, 23); set(Calendar.MINUTE, 59); set(Calendar.SECOND, 59); set(Calendar.MILLISECOND, 999) }.time
    private fun getDaysAgo(days: Int, from: Date = Date()): Date = Calendar.getInstance().apply { time = from; add(Calendar.DAY_OF_YEAR, -days) }.time
    private fun isToday(date: Date): Boolean { val today = getStartOfToday(); val selected = getStartOfDay(date); return today == selected }
}

private data class DialogState(val showCreateActivityDialog: Boolean, val activityTypeToDelete: ActivityType?, val showCreateFoodDialog: Boolean, val foodTypeToDelete: FoodType?)
