package org.devvikram.firstpmpproject.model





data class Task(
    val id: String,
    val title: String,
    val  isCompleted: Boolean = false
)
