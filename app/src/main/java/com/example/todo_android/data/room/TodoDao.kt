package com.example.todo_android.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<TodoContent>>

    @Query("SELECT * FROM todos WHERE finished = true")
    fun getFinishedTodo() : Flow<List<TodoContent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(vararg todoContent: TodoContent)

    @Delete
    suspend fun deleteTodo(vararg todoContent: TodoContent)

    @Update
    suspend fun editTodo(vararg todoContent: TodoContent)

}