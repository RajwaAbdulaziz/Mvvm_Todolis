package com.tuwaiq.mvvmtodolis

import androidx.lifecycle.LiveData
import com.tuwaiq.mvvmtodolis.database.Todo
import java.util.*

class TodoVM {

    private val repo  = TodoRepo.get()!!

    var todo:Todo?=null
            set(value){
                field = value
            }

    val todoLiveData:LiveData<List<Todo>?> =
        repo.getAllTodos()

    fun addTodo(todo:Todo){
        repo.addTodo(todo)
    }


    val title:String?
        get() = todo?.title

    val duoDate:Date?
        get() = todo?.createDate


    val isCom:Boolean?
        get() = todo?.isCompleted

}