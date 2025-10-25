package com.example.fitnesstracker.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ActivityRecord::class, FoodRecord::class, ActivityType::class, FoodType::class], version = 4)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun activityDao(): ActivityDao
    abstract fun foodDao(): FoodDao
    abstract fun activityTypeDao(): ActivityTypeDao
    abstract fun foodTypeDao(): FoodTypeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_tracker_database"
                )
                .fallbackToDestructiveMigration() // Deletes old data on version change
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
