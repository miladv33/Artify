package com.example.artify.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MyApp() {
    // Create a NavController
    val navController = rememberNavController()

    // Create a NavHost with a route for each screen
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home { id ->
                // Navigate to DetailScreen with an argument
                navController.navigate("detail/$id")
            }
        }
        composable(
            // Define a route with a parameter
            route = "detail/{id}",
            // Define an argument for the parameter
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            // Get the argument from the backStackEntry
            val id = backStackEntry.arguments?.getInt("id")
            DetailScreen(objectID = id ?: 0)
        }
    }
}
