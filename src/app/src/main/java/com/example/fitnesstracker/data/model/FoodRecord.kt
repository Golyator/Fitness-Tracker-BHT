package com.example.fitnesstracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

enum class PortionSize {
    SMALL, MEDIUM, LARGE
}

@Entity(tableName = "food_records")
data class FoodRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val calories: Int,
    val portionSize: PortionSize,
    val date: Date
)
