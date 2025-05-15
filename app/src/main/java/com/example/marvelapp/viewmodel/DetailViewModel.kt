package com.example.marvelapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.marvelapp.data.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {

    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    fun setCharacter(character: Character) {
        _selectedCharacter.value = character
    }
}
