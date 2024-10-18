package com.example.todo_android.ui.TodoList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            todoItem(TodoContent(3, "A", "dadadaf", false))
        }
        items(40) { index ->
            Text(text = "Item: $index")
        }

    }
}

@Composable
fun todoItem(todoContent: TodoContent) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))            // Rounded corners
            .fillMaxWidth()
            .background(Color.LightGray)                // Background color for the item
    ) {
        // Display the user's name
        Text(
            text = todoContent.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Alignment.CenterHorizontally)
        )
        // Display the user's email
        Text(
            text = todoContent.content,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}