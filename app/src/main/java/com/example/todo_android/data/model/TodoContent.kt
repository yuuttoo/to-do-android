package com.example.todo_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "todos")
data class TodoContent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var content: String,
    var finished: Boolean
)