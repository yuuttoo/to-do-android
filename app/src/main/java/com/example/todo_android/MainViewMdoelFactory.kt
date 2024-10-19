package com.example.todo_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo_android.data.room.TodoDao
import com.example.todo_android.ui.MainViewModel

class MainViewModelFactory(private val todoDao: TodoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(todoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}