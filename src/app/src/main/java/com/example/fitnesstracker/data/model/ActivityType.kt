package com.example.fitnesstracker.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "activity_types",
    indices = [Index(value = ["name"], unique = true)]
)
data class ActivityType(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val metValue: Double // Metabolic Equivalent of Task
)
