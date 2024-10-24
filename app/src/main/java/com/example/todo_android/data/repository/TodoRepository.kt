package com.example.todo_android.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todo_android.data.model.TodoContent
import com.example.todo_android.data.room.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    fun getAllTodos(): Flow<List<TodoContent>> = todoDao.getAll()

    fun getFinishedTodos(): Flow<List<TodoContent>> = todoDao.getFinishedTodo()

    suspend fun insertTodo(todo: TodoContent) {
        todoDao.insertTodo(todo)
    }

    suspend fun editTodo(todo: TodoContent) {
        todoDao.editTodo(todo)
    }

    suspend fun deleteTodo(todo: TodoContent) {
        todoDao.deleteTodo(todo)
    }
}