package com.example.marvelapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.marvelapp.ui.components.CharacterCard
import com.example.marvelapp.ui.components.MarvelTopBar
import com.example.marvelapp.ui.navigation.Screen
import com.example.marvelapp.viewmodel.FavoritesViewModel
import com.google.gson.Gson

@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = FavoritesViewModel()) {
    val favorites by viewModel.favorites.collectAsState()

    Scaffold(topBar = {
        MarvelTopBar(title = "Favoritos", navController = navController, showBackButton = false)
    }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(favorites) { character ->
                CharacterCard(character = character) {
                    val characterJson = Gson().toJson(character)
                    navController.navigate(Screen.Detail.createRoute(characterJson))
                }
            }
        }
    }
}
