package com.example.rickandmorty.ui.characterdetail.data

import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailApiService {

    @GET("api/character/{id}")
    fun getCharacterDetail(
        @Path("id") id: Int    ): io.reactivex.rxjava3.core.Observable<Characters>

}