package com.reyhan.data.local.room

import androidx.room.TypeConverter
import com.reyhan.data.local.Priority

class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.toString()
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}