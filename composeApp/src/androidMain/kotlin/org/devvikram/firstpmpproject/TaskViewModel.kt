package org.devvikram.firstpmpproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.devvikram.firstpmpproject.model.Task
import org.devvikram.firstpmpproject.repository.TaskRepository

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel()  {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: MutableStateFlow<List<Task>> = _tasks

    init {
        refreshTasks()
    }
    private fun refreshTasks() {
        _tasks.value = taskRepository.getTasks()

    }
    fun addTask(title: String) {
        taskRepository.addTask(title)
        refreshTasks()
    }
    fun removeTask(taskId: String) {
        taskRepository.removeTask(taskId)
        refreshTasks()
    }
    fun updateTask(taskId: String, newTitle: String,isCompleted : Boolean) {
        taskRepository.updateTask(taskId, newTitle,isCompleted)
        refreshTasks()
    }
    fun toggleCompleteState(taskId: String, isCompleted: Boolean) {
        taskRepository.toggleCompleteState(taskId, isCompleted)
        refreshTasks()
    }
}