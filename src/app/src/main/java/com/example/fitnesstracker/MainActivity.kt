package com.example.fitnesstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstracker.data.model.ActivityRecord
import com.example.fitnesstracker.data.model.FoodRecord
import com.example.fitnesstracker.ui.*
import com.example.fitnesstracker.ui.theme.FitnesstrackerTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnesstrackerTheme {
                FitnessTrackerApp()
            }
        }
    }
}

@Composable
fun FitnessTrackerApp(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScaffold(
                modifier = modifier,
                viewModel = viewModel,
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        composable("settings") {
            SettingsScreen(
                userSettings = uiState.user,
                onSave = {
                    viewModel.saveUserSettings(it)
                    navController.popBackStack()
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onSettingsClick: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Fitness Tracker") },
                navigationIcon = {
                    IconButton(onClick = onSettingsClick) { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Handle calendar click */ }) {
                        Icon(Icons.Filled.DateRange, contentDescription = "Calendar")
                    }
                }
            )
        }
    ) { innerPadding ->
        MainScreenContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenContent(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val activityTypes by viewModel.activityTypes.collectAsState()
    val foodTypes by viewModel.foodTypes.collectAsState()
    val chartData by viewModel.chartData.collectAsState()
    val selectedDate = SimpleDateFormat("dd. MMMM yyyy", Locale.GERMAN).format(uiState.selectedDate)
    val isToday = isSameDay(uiState.selectedDate, Date())
    val tabs = listOf(MainScreenTab.ACTIVITIES, MainScreenTab.FOOD)

    // --- Dialogs ---
    if (uiState.showCreateActivityDialog) { CreateActivityTypeDialog(onDismiss = viewModel::onDismissCreateActivityDialog, onConfirm = viewModel::createActivityType) }
    uiState.activityTypeToDelete?.let { DeleteActivityTypeDialog(activityType = it, onDismiss = viewModel::onDismissDeleteActivityType, onConfirm = viewModel::onConfirmDeleteActivityType) }
    if (uiState.showCreateFoodDialog) { CreateFoodTypeDialog(onDismiss = viewModel::onDismissCreateFoodDialog, onConfirm = viewModel::createFoodType) }
    uiState.foodTypeToDelete?.let { DeleteFoodTypeDialog(foodType = it, onDismiss = viewModel::onDismissDeleteFoodType, onConfirm = viewModel::onConfirmDeleteFoodType) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Date Navigation
        item {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = viewModel::selectPreviousDay) { Icon(Icons.Default.ArrowBack, "Previous Day") }
                Text(text = selectedDate, modifier = Modifier.padding(horizontal = 16.dp), style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = viewModel::selectNextDay, enabled = !isToday) { Icon(Icons.Default.ArrowForward, "Next Day") }
            }
        }

        // Statistics Chart
        item {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                StatisticsChart(modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp), chartData = chartData)
            }
        }

        // Daily Calorie Summary
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Verbraucht", style = MaterialTheme.typography.labelLarge)
                    Text(buildAnnotatedString { withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.headlineSmall.fontSize)) { append("${uiState.totalCaloriesBurned}") }; append(" kcal") })
                    Text("(${uiState.bmr} + ${uiState.activitiesForSelectedDate.sumOf { it.caloriesBurned }})", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Eingenommen", style = MaterialTheme.typography.labelLarge)
                    Text("${uiState.totalCaloriesConsumed} kcal", fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                }
            }
        }

        // Tab Layout
        item {
            TabRow(selectedTabIndex = uiState.selectedTab.ordinal) {
                tabs.forEach {
                    Tab(
                        selected = uiState.selectedTab == it,
                        onClick = { viewModel.onTabSelected(it) },
                        text = { Text(text = if (it == MainScreenTab.ACTIVITIES) "Aktivitäten" else "Nahrungsmittel") }
                    )
                }
            }
        }

        // Tab Content
        when (uiState.selectedTab) {
            MainScreenTab.ACTIVITIES -> {
                item {
                    ElevatedCard(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
                        ActivityInput(
                            modifier = Modifier.padding(16.dp),
                            activityTypes = activityTypes,
                            onAddActivity = viewModel::addActivity,
                            onCreateNewActivity = viewModel::onShowCreateActivityDialog,
                            onDeleteActivityType = viewModel::onStartDeleteActivityType
                        )
                    }
                }
                items(uiState.activitiesForSelectedDate, key = { "activity_${it.id}" }) { activity ->
                    Box(modifier = Modifier.animateItemPlacement(tween(durationMillis = 300))) {
                        ActivityItem(activity, onDelete = { viewModel.deleteActivity(activity.id) })
                    }
                }
            }
            MainScreenTab.FOOD -> {
                item {
                    ElevatedCard(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
                        FoodInput(
                            modifier = Modifier.padding(16.dp),
                            foodTypes = foodTypes,
                            onAddFood = viewModel::addFood,
                            onCreateNewFood = viewModel::onShowCreateFoodDialog,
                            onDeleteFoodType = viewModel::onStartDeleteFoodType
                        )
                    }
                }
                items(uiState.foodForSelectedDate, key = { "food_${it.id}" }) { food ->
                    Box(modifier = Modifier.animateItemPlacement(tween(durationMillis = 300))) {
                        FoodItem(food, onDelete = { viewModel.deleteFood(food.id) })
                    }
                }
            }
        }
    }
}


@Composable
fun ActivityItem(activity: ActivityRecord, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) { Text(activity.name, style = MaterialTheme.typography.bodyLarge); Text("${activity.durationMinutes} min, ${activity.intensity.name}", style = MaterialTheme.typography.bodySmall, color = Color.Gray) }
            Row(verticalAlignment = Alignment.CenterVertically) { Text("${activity.caloriesBurned} kcal", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold); IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, "Delete Activity") } }
        }
    }
}

@Composable
fun FoodItem(food: FoodRecord, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) { Text(food.name, style = MaterialTheme.typography.bodyLarge); Text("Portion: ${food.portionSize.name}", style = MaterialTheme.typography.bodySmall, color = Color.Gray) }
            Row(verticalAlignment = Alignment.CenterVertically) { Text("${food.calories} kcal", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold); IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, "Delete Food") } }
        }
    }
}

private fun isSameDay(date1: Date, date2: Date): Boolean {
    val cal1 = Calendar.getInstance().apply { time = date1 }; val cal2 = Calendar.getInstance().apply { time = date2 }
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

@Preview(showBackground = true)
@Composable
fun FitnessTrackerAppPreview() {
    FitnesstrackerTheme { val viewModel: MainViewModel = viewModel(); MainScaffold(viewModel = viewModel, onSettingsClick = {}) }
}
