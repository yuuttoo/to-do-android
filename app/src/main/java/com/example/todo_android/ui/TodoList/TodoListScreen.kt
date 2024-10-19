package com.example.todo_android.ui.TodoList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.todo_android.R
import com.example.todo_android.data.room.TodoContent


@Composable
fun TodoListScreen() {
    TodoList()
}


@Composable
fun TodoList() {
    val listState = rememberLazyListState()

    LazyColumn (
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        item {
            //Text(text = "First item")
            todoItem(TodoContent(1, "A", "dadadaf", false))
        }
        item {
            //Text(text = "First item")
            todoItem(TodoContent(2, "A", "dadadaf", false))
        }
        item {
            //Text(text = "First item")
            todoItem(TodoContent(3, "C", "dadadaddddf", true))
        }


    }
}

@Composable
fun todoItem(todoContent: TodoContent) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .clip(RoundedCornerShape(16.dp))            // Rounded corners
                .fillMaxWidth()
                .background(colorResource(R.color.dark_red)),                // Background color for the item
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                        Text(
                            text = todoContent.title,
                            style = MaterialTheme.typography.bodyLarge,
                            color = colorResource(R.color.pink),
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                        Text(
                            text = todoContent.content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorResource(R.color.pink),
                            modifier = Modifier
                        )
                }
                IconButton(
                    onClick = { /* do something */ },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Mark as completed",
                        tint = if(!todoContent.finished) colorResource(R.color.pink) else Color.Green
                    )
                }
            }
        }
}