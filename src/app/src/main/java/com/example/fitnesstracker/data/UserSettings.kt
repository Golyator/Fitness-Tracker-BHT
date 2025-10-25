package com.example.fitnesstracker.data

enum class Gender {
    MALE, FEMALE, OTHER
}

data class UserSettings(
    val age: Int = 30,
    val height: Int = 180, // in cm
    val weight: Double = 75.0, // in kg
    val gender: Gender = Gender.MALE
)

/**
 * Calculates Basal Metabolic Rate (BMR) using the Mifflin-St Jeor equation.
 */
fun UserSettings.calculateBmr(): Int {
    if (weight <= 0 || height <= 0 || age <= 0) return 0

    val bmr = 10 * weight + 6.25 * height - 5 * age
    return when (gender) {
        Gender.MALE -> (bmr + 5).toInt()
        Gender.FEMALE -> (bmr - 161).toInt()
        Gender.OTHER -> (bmr - 78).toInt() // Average of male and female adjustment
    }
}
