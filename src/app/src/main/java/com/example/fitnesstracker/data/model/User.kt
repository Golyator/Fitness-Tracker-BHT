package com.example.fitnesstracker.data.model

enum class Gender {
    MALE, FEMALE, OTHER
}

data class User(
    val age: Int? = null,
    val height: Double? = null, // in cm
    val weight: Double? = null, // in kg
    val gender: Gender? = null
)
