package com.example.rickandmorty.ui.characterdetail.data.datasource

import com.example.rickandmorty.ui.bottomnavigation.characters.data.CharactersApiService
import com.example.rickandmorty.ui.characterdetail.data.CharacterDetailApiService
import javax.inject.Inject

class CharacterDetailDataSource @Inject constructor(
   private val apiService: CharacterDetailApiService
) {
    fun getCharacterDetail(id:Int) =
        apiService.getCharacterDetail(id)
}
