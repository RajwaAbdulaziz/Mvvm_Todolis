package com.tuwaiq.mvvmtodolis.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM Todo")
    fun getAllTodos():LiveData<List<Todo>?>

    @Query("SELECT * FROM Todo WHERE id=(:id)")
    fun getTodo(id:Int):LiveData<Todo?>

    @Insert(onConflict = OnConflictStrategy.IGNORE )
    fun addTodo(todo:Todo)

    @Delete
    fun deleteTodo(todo:Todo)

    @Update
    fun updateTodo(todo:Todo)


}