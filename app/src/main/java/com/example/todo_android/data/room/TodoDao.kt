package com.example.todo_android.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAll(): List<TodoContent>

    @Query("SELECT * FROM todos WHERE finished = true")
    fun getFinishedTodo() :List<TodoContent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(vararg todoContent: TodoContent)

    @Delete
    fun deleteTodo(vararg todoContent: TodoContent)

    @Update
    fun editTodo(vararg todoContent: TodoContent)

}