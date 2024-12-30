package org.devvikram.firstpmpproject.presentation

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.devvikram.firstpmpproject.model.Task
import org.devvikram.firstpmpproject.repository.TaskRepository

@Composable
fun TaskListScreen(taskRepository: TaskRepository) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val tasks = remember { mutableStateOf(taskRepository.getTasks()) }
    val newTaskTitle = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = newTaskTitle.value,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                placeholder = { Text("Enter task title", color = Color.Gray) },
                onValueChange = { newTaskTitle.value = it }
            )

            Button(
                onClick = {
                    if (newTaskTitle.value.isBlank()) {
                        Toast.makeText(context, "Please enter a task title", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    taskRepository.addTask(newTaskTitle.value)
                    newTaskTitle.value = ""
                    tasks.value = taskRepository.getTasks()
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(horizontal = 4.dp),
                elevation = ButtonDefaults.elevation(6.dp)
            ) {
                Text(
                    text = "Add",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }


        LazyColumn (
            modifier = Modifier
               .fillMaxWidth()
               .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks.value.size) {
                val task = tasks.value[it]
                TaskCard(
                    task = task,
                    tasks = tasks,
                    taskRepository = taskRepository
                )
            }
        }

    }

}

@Composable
fun TaskCard(
    task: Task,
    tasks: androidx.compose.runtime.MutableState<List<Task>>,
    taskRepository: TaskRepository
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = Color(0xFFF8F9FA)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.title,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                color = Color.Black
            )
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete Task",
                modifier = Modifier.padding(start = 8.dp).clickable {
                    taskRepository.removeTask(task.id)
                    tasks.value = taskRepository.getTasks()
                }
            )
        }
    }
}
