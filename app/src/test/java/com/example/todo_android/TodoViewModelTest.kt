package com.example.todo_android

import app.cash.turbine.test
import com.example.todo_android.data.model.TodoContent
import com.example.todo_android.data.repository.TodoRepository
import com.example.todo_android.ui.TodoList.TodoViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var mockRepository: TodoRepository
    private lateinit var viewModel: TodoViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockRepository = mockk(relaxed = true)

        // 設置基本的mock回應
        coEvery { mockRepository.getAllTodos() } returns flowOf(emptyList())

        viewModel = TodoViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When initialize the todos should be empty list`() = runTest {
        viewModel.todos.test {
            assertEquals(emptyList<TodoContent>(), awaitItem())
        }
    }

    @Test
    fun `toggleFinishedTodos應該切換顯示狀態`() = runTest {
        val todos = listOf(
            TodoContent(id = 1, title = "Todo 1", content = "Content 1", finished = false),
            TodoContent(id = 2, title = "Todo 2", content = "Content 2", finished = true)
        )

        coEvery { mockRepository.getAllTodos() } returns flowOf(todos)

        viewModel = TodoViewModel(mockRepository)

        // 初始狀態應該顯示所有todos
        viewModel.todos.test {
            assertEquals(todos, awaitItem())
        }

        // 切換狀態
        viewModel.toggleFinishedTodos()

        // 應該只顯示已完成的todos
        viewModel.todos.test {
            val filteredTodos = awaitItem()
            assertEquals(1, filteredTodos.size)
            assert(filteredTodos.all { it.finished })
        }
    }

    @Test
    fun `addTodo應該調用repository的insertTodo`() = runTest {
        val todo = TodoContent(id = 1, title = "Test", content = "Test Content", finished = false)
        viewModel.addTodo(todo)
        coVerify { mockRepository.insertTodo(todo) }
    }

    @Test
    fun `deleteTodo應該調用repository的deleteTodo`() = runTest {
        val todo = TodoContent(id = 1, title = "Test", content = "Test Content", finished = false)
        viewModel.deleteTodo(todo)
        coVerify { mockRepository.deleteTodo(todo) }
    }

    @Test
    fun `editTodo應該調用repository的editTodo`() = runTest {
        val todo = TodoContent(id = 1, title = "Test", content = "Test Content", finished = false)
        viewModel.editTodo(todo)
        coVerify { mockRepository.editTodo(todo) }
    }

    @Test
    fun `setTodoFinished應該用更新後的finished狀態調用editTodo`() = runTest {
        val todo = TodoContent(id = 1, title = "Test", content = "Test Content", finished = false)
        val expectedTodo = todo.copy(finished = true)//set to finish

        viewModel.setTodoFinished(todo)

        coVerify { mockRepository.editTodo(expectedTodo) }
    }


}

