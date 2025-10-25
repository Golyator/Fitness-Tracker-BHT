package com.example.fitnesstracker.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "food_types",
    indices = [Index(value = ["name"], unique = true)]
)
data class FoodType(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val caloriesMediumPortion: Int
)
