package com.example.rickandmorty.ui.bottomnavigation.characters.data

import android.database.Observable
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CharactersApiService {
    @GET("api/characters")
    fun getCharacters(
    ): io.reactivex.rxjava3.core.Observable<List<CharactersResponse>>

}