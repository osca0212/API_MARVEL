package com.example.marvelapp.data.model

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
) {
    val fullUrl: String
        get() = "$path.$extension".replace("http://", "https://")
}
