package com.tuwaiq.mvvmtodolis.database

import android.app.Application
import com.tuwaiq.mvvmtodolis.TodoRepo

class TodoApp:Application() {
    override fun onCreate() {
        super.onCreate()
        TodoRepo.initialize(this)
    }
}