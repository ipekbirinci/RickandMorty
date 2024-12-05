package com.example.rickandmorty.ui.bottomnavigation.characters.data.repo

import android.database.Observable
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse

interface CharactersRepository {
    fun getCharacters(): io.reactivex.rxjava3.core.Observable<List<CharactersResponse>>

}