package com.example.todo_android.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo_android.data.model.TodoContent

@Database(entities = [TodoContent::class], version = 2)
abstract class TodoDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todos"
                ).fallbackToDestructiveMigration() // 升級時刪除前版資料庫內容
                .build()
                INSTANCE = instance
                instance
            }
        }

    }

    abstract fun todoDao() :TodoDao

}