package com.example.fitnesstracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

enum class Intensity {
    LOW, MEDIUM, HIGH
}

@Entity(tableName = "activity_records")
data class ActivityRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val durationMinutes: Int, // Duration in minutes
    val intensity: Intensity,
    val caloriesBurned: Int,
    val date: Date
)
