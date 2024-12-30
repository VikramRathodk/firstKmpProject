package org.devvikram.firstpmpproject.repository

import org.devvikram.firstpmpproject.model.Task

class TaskRepository {

    private val tasks = mutableListOf<Task>()

    fun addTask(title: String) {
        tasks.add(
            Task(
                id = tasks.size.toString(),
                title = title,
                isCompleted = false
            )
        )
    }

    fun removeTask(taskId: String) {
        tasks.removeAll { it.id == taskId }
    }

    fun getTasks(): List<Task> = tasks.toList()



}