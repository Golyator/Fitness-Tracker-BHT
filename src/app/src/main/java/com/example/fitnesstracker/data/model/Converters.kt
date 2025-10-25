package com.example.fitnesstracker.data.model

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromIntensity(value: String?): Intensity? {
        return value?.let { Intensity.valueOf(it) }
    }

    @TypeConverter
    fun intensityToString(intensity: Intensity?): String? {
        return intensity?.name
    }

    @TypeConverter
    fun fromPortionSize(value: String?): PortionSize? {
        return value?.let { PortionSize.valueOf(it) }
    }

    @TypeConverter
    fun portionSizeToString(portionSize: PortionSize?): String? {
        return portionSize?.name
    }
}
