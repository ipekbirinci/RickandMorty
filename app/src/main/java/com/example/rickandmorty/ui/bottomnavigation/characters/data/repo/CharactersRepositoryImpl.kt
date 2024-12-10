package com.example.rickandmorty.ui.bottomnavigation.characters.data.repo

import com.example.rickandmorty.ui.bottomnavigation.characters.data.datasource.CharactersDataSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val dataSource: CharactersDataSource):CharactersRepository{
    override fun getCharacters() = dataSource.getCharacters()

}