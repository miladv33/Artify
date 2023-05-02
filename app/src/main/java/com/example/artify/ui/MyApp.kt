package com.example.artify.ui

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Artify() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "search_screen") {
        composable("search_screen") {
            SearchScreen { id -> navigateToDetail(navController, id) }
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            DetailScreen(objectID = getObjectId(it))
        }
    }
}

fun navigateToDetail(navController: NavController, id: Int) {
    navController.navigate("detail/$id")
}

fun getObjectId(backStackEntry: NavBackStackEntry): Int {
    return backStackEntry.arguments?.getInt("id") ?: 0
}