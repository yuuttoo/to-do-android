package com.example.todo_android.ui

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val todoDao : TodoDao) : ViewModel() {

//    private val _uiState = MutableStateFlow(TodoUiState())
//    val uiState : StateFlow<TodoUiState> = _uiState.asStateFlow()
    private val _todos = MutableStateFlow<List<TodoContent>>(emptyList())
    val todos : StateFlow<List<TodoContent>> = _todos.asStateFlow()


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
            loadTodos()
        }
    }

    fun editTodo(todoItem: TodoContent) {//edit text
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.editTodo(todoItem)
            loadTodos()
        }
    }

    fun setTodoFinished(todoItem: TodoContent) {//edit text
        todoItem.finished = true
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.editTodo(todoItem)
            loadTodos()
        }
    }


}