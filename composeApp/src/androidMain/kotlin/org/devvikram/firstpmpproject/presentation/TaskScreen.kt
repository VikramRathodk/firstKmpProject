package org.devvikram.firstpmpproject.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.devvikram.firstpmpproject.TaskViewModel
import org.devvikram.firstpmpproject.model.Task

@Composable
fun TaskListScreen(taskViewModel: TaskViewModel) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val tasks by taskViewModel.tasks.collectAsState()
    val newTaskTitle = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Blue,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = "Todo App", modifier = Modifier.padding(16.dp), color = Color.White)
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
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
                            Toast.makeText(context, "Please enter a task title", Toast.LENGTH_SHORT)
                                .show()
                            return@Button
                        }
                        taskViewModel.addTask(newTaskTitle.value)
                        newTaskTitle.value = ""
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

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks.size) {
                    val task = tasks[it]
                    TaskCard(
                        task = task,
                        taskViewModel = taskViewModel
                    )
                }
            }

        }
    }

}

@Composable
fun TaskCard(
    task: Task,
    taskViewModel: TaskViewModel
) {
    val showEditDialog = remember { mutableStateOf(false) }
    val editedTitle = remember { mutableStateOf(task.title) }
    val editedCompletionState = remember { mutableStateOf(task.isCompleted) }

    LaunchedEffect(task.id) {
        editedTitle.value = task.title
        editedCompletionState.value = task.isCompleted
    }


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
            Checkbox(
                checked = editedCompletionState.value,
                onCheckedChange = { isChecked ->
                    editedCompletionState.value = isChecked
                    taskViewModel.toggleCompleteState(
                        taskId = task.id,
                        isCompleted = isChecked
                    )
                },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = task.title,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                color = Color.Black,
                style = MaterialTheme.typography.body1.copy(
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            )

            Icon(
                Icons.Filled.Edit,
                contentDescription = "Edit Task",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        showEditDialog.value = true
                    }
            )
            if (showEditDialog.value) {
                Dialog(onDismissRequest = {
                    showEditDialog.value = false
                }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colors.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Update Task",
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            OutlinedTextField(
                                value = editedTitle.value,
                                onValueChange = { editedTitle.value = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.size(10.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Switch(
                                    checked = editedCompletionState.value,
                                    onCheckedChange = {
                                        editedCompletionState.value = it
                                    },
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = if (editedCompletionState.value) "Completed" else "Incomplete",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }

                            Button(
                                onClick = {
                                    taskViewModel.updateTask(
                                        taskId = task.id,
                                        newTitle = editedTitle.value,
                                        isCompleted = editedCompletionState.value                                   )
                                    showEditDialog.value = false
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                            ) {
                                Text(
                                    text = "Update",
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }


            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete Task",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        taskViewModel.removeTask(task.id)
                    }
            )
        }
    }
}
