package com.example.fitnesstracker.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ActivityDao {

    @Query("SELECT * FROM activity_records WHERE date = :date ORDER BY id DESC")
    fun getActivitiesForDate(date: Date): Flow<List<ActivityRecord>>

    @Query("SELECT * FROM activity_records WHERE date BETWEEN :startDate AND :endDate")
    fun getActivitiesBetween(startDate: Date, endDate: Date): Flow<List<ActivityRecord>>

    @Insert
    suspend fun insert(activity: ActivityRecord)

    @Query("DELETE FROM activity_records WHERE id = :activityId")
    suspend fun delete(activityId: Long)
}
