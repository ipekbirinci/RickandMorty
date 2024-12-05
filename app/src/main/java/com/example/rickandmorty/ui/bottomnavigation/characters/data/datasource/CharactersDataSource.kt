package com.example.rickandmorty.ui.bottomnavigation.characters.data.datasource

import com.example.rickandmorty.ui.bottomnavigation.characters.data.CharactersApiService
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
   private val apiService: CharactersApiService
) {
    fun getCharacters() =
        apiService.getCharacters()
}
