package com.example.todo_android.ui

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todo_android.data.room.TodoContent
import com.example.todo_android.data.room.TodoDao
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val todoDao : TodoDao) : ViewModel() {

//    private val _uiState = MutableStateFlow(TodoUiState())
//    val uiState : StateFlow<TodoUiState> = _uiState.asStateFlow()
    private val _todos = MutableStateFlow<List<TodoContent>>(emptyList())
    val todos : StateFlow<List<TodoContent>> = _todos.asStateFlow()

    //dao flow 寫法
    //val todos: Flow<List<TodoContent>> = todoDao.getAll()//_todos should be todos , // Directly exposing Flow

    //val todos : StateFlow<List<TodoContent>> = _todos.

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.value = todoDao.getAll()
        }
    }

    fun getFinished() {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.value = todoDao.getFinishedTodo()
        }
    }

    fun addTodo(todoItem: TodoContent) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.insertTodo(todoItem)
            loadTodos()
        }
    }

    fun deleteTodo(todoItem: TodoContent) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(todoItem)
            //loadTodos()
        }
    }

    fun editTodo(todoItem: TodoContent) {//edit text
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.editTodo(todoItem)
            //loadTodos()
        }
    }

    fun setTodoFinished(todoItem: TodoContent) {//edit text
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.editTodo(todoItem.copy(finished = true))

            val updatedTodos = _todos.value.map { todo ->//比對所有list,找到符合的id後修改,不符合的維持原狀
                if (todo.id == todoItem.id) todo.copy(finished = true) else todo
            }
            _todos.value = updatedTodos //整體的, Update the UI state, immediately triggers a recomposition in the UI. 但這是手動強迫UI修改
        }
    }


}