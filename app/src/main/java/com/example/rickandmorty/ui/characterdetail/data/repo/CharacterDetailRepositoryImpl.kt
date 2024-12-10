package com.example.rickandmorty.ui.characterdetail.data.repo

import com.example.rickandmorty.ui.characterdetail.data.datasource.CharacterDetailDataSource
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(private val dataSource: CharacterDetailDataSource):CharacterDetailRepository{
    override fun getCharacterDetail(id: Int) = dataSource.getCharacterDetail(id)

}