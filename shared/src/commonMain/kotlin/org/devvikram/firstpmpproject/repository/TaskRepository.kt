package org.devvikram.firstpmpproject.repository

import org.devvikram.firstpmpproject.model.Task

class TaskRepository {

    private val tasks = mutableListOf<Task>()
    private var nextTaskId = 0
    fun addTask(title: String) {
        tasks.add(
            Task(
                id =nextTaskId.toString(),
                title = title,
                isCompleted = false
            )
        )
        nextTaskId++
    }

    fun removeTask(taskId: String) {
        tasks.removeAll { it.id == taskId }
    }
    fun updateTask(taskId: String, newTitle: String, isCompleted : Boolean) {
        val taskIndex = tasks.indexOfFirst { it.id == taskId}
        if (taskIndex != -1) {
            tasks[taskIndex] = tasks[taskIndex].copy(title = newTitle, isCompleted = isCompleted)
        }
    }


    fun getTasks(): List<Task> = tasks.toList()
    fun toggleCompleteState(taskId: String, isCompleted: Boolean) {
        val taskIndex = tasks.indexOfFirst { it.id == taskId }
        if (taskIndex != -1) {
            tasks[taskIndex] = tasks[taskIndex].copy(isCompleted = isCompleted)
        }
    }



}