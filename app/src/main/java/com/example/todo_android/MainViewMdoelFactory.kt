package com.example.todo_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo_android.data.repository.TodoRepository
import com.example.todo_android.data.room.TodoDao
import com.example.todo_android.ui.TodoList.TodoViewModel
import javax.inject.Inject

//class MainViewModelFactory @Inject constructor(private val repository: TodoRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return TodoViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}