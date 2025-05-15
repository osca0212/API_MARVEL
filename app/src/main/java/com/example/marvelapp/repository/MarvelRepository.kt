package com.example.marvelapp.repository

import com.example.marvelapp.data.model.Character
import com.example.marvelapp.network.MarvelApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelRepository {

    private val publicKey = "TU_PUBLIC_KEY"
    private val privateKey = "TU_PRIVATE_KEY"

    suspend fun getCharacters(): List<Character> = withContext(Dispatchers.IO) {
        val timeStamp = System.currentTimeMillis().toString()
        val hash = md5(timeStamp + privateKey + publicKey)
        val response = MarvelApiClient.apiService.getCharacters(
            apiKey = publicKey,
            timeStamp = timeStamp,
            hash = hash
        )
        response.data.results
    }

    private fun md5(input: String): String {
        val bytes = java.security.MessageDigest.getInstance("MD5").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
