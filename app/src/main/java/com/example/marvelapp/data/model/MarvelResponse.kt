package com.example.marvelapp.data.model

data class MarvelResponse(
    val data: DataContainer
)

data class DataContainer(
    val results: List<Character>
)
