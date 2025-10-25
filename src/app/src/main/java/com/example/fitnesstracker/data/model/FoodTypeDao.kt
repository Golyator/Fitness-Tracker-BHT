package com.example.fitnesstracker.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodTypeDao {

    @Query("SELECT * FROM food_types ORDER BY name ASC")
    fun getAll(): Flow<List<FoodType>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodType: FoodType)

    @Delete
    suspend fun delete(foodType: FoodType)
}
