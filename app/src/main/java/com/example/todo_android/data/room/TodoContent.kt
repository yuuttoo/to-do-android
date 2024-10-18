package com.example.todo_android.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "todos")
data class TodoContent(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val finished: Boolean
)