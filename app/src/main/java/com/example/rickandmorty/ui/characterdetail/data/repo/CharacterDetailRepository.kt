package com.example.rickandmorty.ui.characterdetail.data.repo

import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters

interface CharacterDetailRepository {
    fun getCharacterDetail(id: Int): io.reactivex.rxjava3.core.Observable<Characters>

}