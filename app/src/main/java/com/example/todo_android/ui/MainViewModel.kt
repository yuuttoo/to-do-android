package com.example.todo_android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_android.data.room.TodoContent
import com.example.todo_android.data.room.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val todoDao : TodoDao) : ViewModel() {

    private val _showFinishedOnly = MutableStateFlow(false)

    val todos: StateFlow<List<TodoContent>> = combine(
        todoDao.getAll(),
        _showFinishedOnly
    ) { todos, showFinished ->
        if (showFinished) {
            todos.filter { it.finished }
        } else {
            todos
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun toggleFinishedTodos() {
        _showFinishedOnly.value = !_showFinishedOnly.value
    }


    fun addTodo(todoItem: TodoContent) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.insertTodo(todoItem)
        }
    }

    fun deleteTodo(todoItem: TodoContent) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(todoItem)
        }
    }

    fun editTodo(todoItem: TodoContent) {//edit text//, newTitle: String, newContent: String
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.editTodo(todoItem)
        }
    }

    fun setTodoFinished(todoItem: TodoContent) {//edit text
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.editTodo(todoItem.copy(finished = true))
        }
    }


}