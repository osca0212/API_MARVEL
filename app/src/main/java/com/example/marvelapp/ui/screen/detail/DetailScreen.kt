package com.example.marvelapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.ui.components.MarvelTopBar

@Composable
fun DetailScreen(character: Character, navController: NavController) {
    Scaffold(
        topBar = {
            MarvelTopBar(title = character.name, navController = navController, showBackButton = true)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter("${character.thumbnail.path}.${character.thumbnail.extension}"),
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = character.description.ifBlank { "Sin descripción disponible." }, style = MaterialTheme.typography.bodyLarge)
        }
    }
}