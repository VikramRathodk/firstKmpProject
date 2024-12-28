package org.devvikram.firstpmpproject.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavController) {

    Navhost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home") {
            HomeScreen()
        }
    }
}
