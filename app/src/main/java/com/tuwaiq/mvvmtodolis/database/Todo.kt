package com.tuwaiq.mvvmtodolis.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var title:String = "",
    var description: String = "",
    var createDate: Date = Date(),
    var douDate: Date? = null,
    var isCompleted:Boolean = false)