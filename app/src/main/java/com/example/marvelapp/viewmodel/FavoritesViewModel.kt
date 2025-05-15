package com.example.marvelapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.marvelapp.data.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel : ViewModel() {

    private val _favorites = MutableStateFlow<List<Character>>(emptyList())
    val favorites: StateFlow<List<Character>> = _favorites

    fun addFavorite(character: Character) {
        if (!_favorites.value.contains(character)) {
            _favorites.value = _favorites.value + character
        }
    }

    fun removeFavorite(character: Character) {
        _favorites.value = _favorites.value - character
    }

    fun isFavorite(character: Character): Boolean {
        return _favorites.value.contains(character)
    }
}
