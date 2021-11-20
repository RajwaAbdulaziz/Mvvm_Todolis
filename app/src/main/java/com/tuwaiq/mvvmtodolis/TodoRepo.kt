package com.tuwaiq.mvvmtodolis

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tuwaiq.mvvmtodolis.database.Todo
import com.tuwaiq.mvvmtodolis.database.TodoDatabase
import java.lang.IllegalArgumentException
import java.util.concurrent.Executors


private const val DATABASE_NAME = "todo-database"
class TodoRepo private constructor( context: Context){

   private val dataBase: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).build()

   private val todoDAO = dataBase.todoDao()
   private val executor = Executors.newSingleThreadExecutor()

    fun getAllTodos(): LiveData<List<Todo>?> = todoDAO.getAllTodos()

    fun getTodo(id:Int):LiveData<Todo?> = todoDAO.getTodo(id)

    fun addTodo(todo: Todo){
        executor.execute {
            todoDAO.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo){
        executor.execute {
            todoDAO.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        executor.execute {
            todoDAO.deleteTodo(todo)
        }
    }



    companion object{
       private var INSTENCE:TodoRepo? = null

        fun initialize(context: Context){
            if (INSTENCE == null){
                INSTENCE = TodoRepo(context)
            }
        }

        fun get():TodoRepo?{
            return if (INSTENCE == null){
                throw IllegalArgumentException("you must initialize your repo")
            }else {  INSTENCE }
        }

    }

}