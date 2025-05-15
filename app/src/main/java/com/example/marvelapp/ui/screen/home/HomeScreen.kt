package com.example.marvelapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.marvelapp.ui.components.CharacterCard
import com.example.marvelapp.ui.components.MarvelTopBar
import com.example.marvelapp.ui.navigation.Screen
import com.example.marvelapp.viewmodel.HomeViewModel
import com.google.gson.Gson

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = HomeViewModel()) {
    val characters by viewModel.characters.collectAsState()

    Scaffold(topBar = {
        MarvelTopBar(title = "Marvel App", navController = navController, showBackButton = false)
    }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(characters) { character ->
                CharacterCard(character = character) {
                    val characterJson = Gson().toJson(character)
                    navController.navigate(Screen.Detail.createRoute(characterJson))
                }
            }
        }
    }
}
