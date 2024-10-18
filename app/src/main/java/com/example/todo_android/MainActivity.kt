package com.example.todo_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.todo_android.data.room.TodoContent
import com.example.todo_android.data.room.TodoDatabase
import com.example.todo_android.ui.TodoList.TodoListScreen
import com.example.todo_android.ui.theme.Todo_androidTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        lifecycleScope.launch(Dispatchers.IO) {
            val db = TodoDatabase.getInstance(applicationContext)

            db.todoDao().insertTodo(TodoContent(1, "A", "dadadaf", false))
            db.todoDao().insertTodo(TodoContent(2, "B", "dadadaf", false))
            db.todoDao().insertTodo(TodoContent(3, "C", "dadadaf", true))

            var allTodoList = db.todoDao().getAll()
            Log.i("allTodoList", "allTodoList = " + allTodoList.size)
            for (i in 0 until allTodoList.size) {
                Log.d("SHOW", "allTodoList = " + allTodoList[i])
            }
        }

        setContent {
            Todo_androidTheme {
                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        Icons.Filled.Create,
                                        contentDescription = "Localized description"
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        Icons.Filled.Check,
                                        contentDescription = "Localized description",
                                    )
                                }
                            },
                            floatingActionButton = {
                                FloatingActionButton(
                                    onClick = { /* do something */ },
                                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                                ) {
                                    Icon(Icons.Filled.Add, "Localized description")
                                }
                            }
                        )
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                            .background(colorResource(R.color.dark_brown))
                    ) {
                        TodoListScreen()
                    }
//                    Text(
//                        modifier = Modifier.padding(innerPadding),
//                        text = "Create to do list here"
//                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Todo_androidTheme {

    }
}