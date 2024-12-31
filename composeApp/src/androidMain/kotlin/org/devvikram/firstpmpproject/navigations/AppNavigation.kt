package org.devvikram.firstpmpproject.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.devvikram.firstpmpproject.Destination
import org.devvikram.firstpmpproject.TaskViewModel
import org.devvikram.firstpmpproject.presentation.HomeScreen
import org.devvikram.firstpmpproject.presentation.TaskListScreen
import org.devvikram.firstpmpproject.repository.TaskRepository

@Composable
fun AppNavigation(navController: NavHostController,modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destination.Task,
        modifier = modifier
    ) {
        composable<Destination.Home>() {
            HomeScreen()
        }
        composable<Destination.Task>() {
            val taskRepository = TaskRepository()
            val taskViewModel = TaskViewModel(taskRepository)
            TaskListScreen(taskViewModel)
        }
    }
}
