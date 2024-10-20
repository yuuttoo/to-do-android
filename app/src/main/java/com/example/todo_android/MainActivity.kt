package com.example.todo_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import com.example.todo_android.data.room.TodoContent
import com.example.todo_android.data.room.TodoDao
import com.example.todo_android.data.room.TodoDatabase
import com.example.todo_android.data.room.TodoDatabase.Companion.getInstance
import com.example.todo_android.ui.MainViewModel
import com.example.todo_android.ui.TodoList.TodoListScreen
import com.example.todo_android.ui.theme.Todo_androidTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private lateinit var vm: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = getInstance(applicationContext)
        val factory = MainViewModelFactory(db.todoDao())

        vm = ViewModelProvider(this, factory)[MainViewModel::class.java]

        enableEdgeToEdge()

        setContent {
            //val todos by vm.todos.collectAsState()
            val todos by vm.todos.collectAsState(initial = emptyList())

            var showAddDialog by remember { mutableStateOf(false) }

            Todo_androidTheme {
                Scaffold(
                    modifier = Modifier
                        .background(colorResource(R.color.dark_brown)),
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
                                    onClick = { showAddDialog = true },
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
                    ) {
                        TodoListScreen(
                            todos = todos,
                            onSetFinish = { todo ->
                                Log.i("todo", "${todo.content} with ${todo.id}")
                                vm.setTodoFinished(todo)
                            }
                        )
                    }
                    //To do dialog
                    AddTodoDialog(
                        showDialog = showAddDialog,
                        onDismiss = { showAddDialog = false },
                        onConfirm = { title, content ->
                                vm.addTodo(
                                    TodoContent(
                                        title = title,
                                        content = content,
                                        finished = false
                                    )
                                )
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun AddTodoDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (title: String, content: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Add New Todo") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = content,
                        onValueChange = { content = it },
                        label = { Text("Content") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),  // Make content field bigger
                        maxLines = 5
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (title.isNotBlank()) {
                            onConfirm(title, content)
                            title = ""
                            content = ""
                            onDismiss()
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Todo_androidTheme {

    }
}