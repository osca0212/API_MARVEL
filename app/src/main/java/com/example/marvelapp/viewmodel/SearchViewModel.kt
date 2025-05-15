package com.example.marvelapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.repository.MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repository = MarvelRepository()

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _searchResults = MutableStateFlow<List<Character>>(emptyList())
    val searchResults: StateFlow<List<Character>> = _searchResults

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _characters.value = repository.getCharacters()
            _searchResults.value = _characters.value
        }
    }

    fun search(query: String) {
        _searchResults.value = _characters.value.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}
