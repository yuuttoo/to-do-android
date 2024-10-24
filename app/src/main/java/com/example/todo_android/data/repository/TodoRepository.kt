package com.example.todo_android.data.repository

import com.example.todo_android.data.model.TodoContent
import com.example.todo_android.data.room.TodoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TodoRepository @Inject constructor(private val todoDao: TodoDao) {
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