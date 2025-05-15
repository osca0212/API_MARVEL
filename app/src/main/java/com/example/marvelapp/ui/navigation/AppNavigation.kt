package com.example.marvelapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.ui.screens.*
import com.google.gson.Gson

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorites : Screen("favorites")
    object Search : Screen("search")
    object Detail : Screen("detail/{character}") {
        fun createRoute(characterJson: String) = "detail/$characterJson"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("character") { type = NavType.StringType })
        ) { backStackEntry ->
            val characterJson = backStackEntry.arguments?.getString("character") ?: ""
            val character = Gson().fromJson(characterJson, Character::class.java)
            DetailScreen(character = character, navController = navController)
        }
    }
}
