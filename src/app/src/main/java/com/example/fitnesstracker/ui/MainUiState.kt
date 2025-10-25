package com.example.fitnesstracker.ui

import com.example.fitnesstracker.data.UserSettings
import com.example.fitnesstracker.data.calculateBmr
import com.example.fitnesstracker.data.model.*
import java.util.Date

data class MainUiState(
    val user: UserSettings = UserSettings(),
    val selectedDate: Date = Date(),
    val activitiesForSelectedDate: List<ActivityRecord> = emptyList(),
    val foodForSelectedDate: List<FoodRecord> = emptyList(),
    val totalCaloriesConsumed: Int = 0,
    val showCreateActivityDialog: Boolean = false,
    val activityTypeToDelete: ActivityType? = null,
    val showCreateFoodDialog: Boolean = false,
    val foodTypeToDelete: FoodType? = null
) {
    val bmr: Int
        get() = user.calculateBmr()

    val totalCaloriesBurned: Int
        get() = bmr + activitiesForSelectedDate.sumOf { it.caloriesBurned }
}
