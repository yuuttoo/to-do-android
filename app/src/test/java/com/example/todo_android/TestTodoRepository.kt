package com.example.todo_android

import com.example.todo_android.data.model.TodoContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map


class TestTodoRepository {
    private val todosFlow = MutableStateFlow<List<TodoContent>>(emptyList())

    fun emitTodos(todos: List<TodoContent>) {
        todosFlow.value = todos
    }

    fun getAllTodos(): Flow<List<TodoContent>> = todosFlow

    fun getFinishedTodos(): Flow<List<TodoContent>> =
        todosFlow.map { todos -> todos.filter { it.finished } }

    fun insertTodo(todo: TodoContent) {
        val currentList = todosFlow.value.toMutableList()
        currentList.add(todo)
        todosFlow.value = currentList
    }

    fun deleteTodo(todo: TodoContent) {
        val currentList = todosFlow.value.toMutableList()
        currentList.remove(todo)
        todosFlow.value = currentList
    }

    fun editTodo(todo: TodoContent) {
        val currentList = todosFlow.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            currentList[index] = todo
            todosFlow.value = currentList
        }
    }
}