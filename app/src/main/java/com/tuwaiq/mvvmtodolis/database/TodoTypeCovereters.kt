package com.tuwaiq.mvvmtodolis.database

import androidx.room.TypeConverter
import java.util.*

class TodoTypeConverters {

    @TypeConverter
    fun fromDate(date: Date?):Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millSec:Long?):Date?{
        return millSec?.let { Date(it) }
    }


}