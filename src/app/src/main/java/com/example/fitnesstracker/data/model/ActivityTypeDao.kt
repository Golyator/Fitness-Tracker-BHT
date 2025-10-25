package com.example.fitnesstracker.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityTypeDao {

    @Query("SELECT * FROM activity_types ORDER BY name ASC")
    fun getAll(): Flow<List<ActivityType>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignore if an activity with the same name already exists
    suspend fun insert(activityType: ActivityType)

    @Delete
    suspend fun delete(activityType: ActivityType)
}
