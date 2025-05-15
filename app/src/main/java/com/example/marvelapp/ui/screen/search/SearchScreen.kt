package com.example.marvelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marvelapp.ui.components.CharacterCard
import com.example.marvelapp.ui.components.MarvelTopBar
import com.example.marvelapp.ui.navigation.Screen
import com.example.marvelapp.viewmodel.SearchViewModel
import com.google.gson.Gson

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = SearchViewModel()) {
    var query by remember { mutableStateOf("") }
    val results by viewModel.searchResults.collectAsState()

    Scaffold(topBar = {
        MarvelTopBar(title = "Buscar", navController = navController, showBackButton = false)
    }) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.search(it)
                },
                label = { Text("Buscar personaje") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(results) { character ->
                    CharacterCard(character = character) {
                        val characterJson = Gson().toJson(character)
                        navController.navigate(Screen.Detail.createRoute(characterJson))
                    }
                }
            }
        }
    }
}
