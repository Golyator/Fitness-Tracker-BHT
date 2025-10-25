package com.example.fitnesstracker.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_records WHERE date = :date")
    fun getFoodForDate(date: Date): Flow<List<FoodRecord>>

    @Query("SELECT * FROM food_records WHERE date BETWEEN :startDate AND :endDate")
    fun getFoodBetween(startDate: Date, endDate: Date): Flow<List<FoodRecord>>

    @Insert
    suspend fun insert(food: FoodRecord)

    @Query("DELETE FROM food_records WHERE id = :foodId")
    suspend fun delete(foodId: Long)
}
