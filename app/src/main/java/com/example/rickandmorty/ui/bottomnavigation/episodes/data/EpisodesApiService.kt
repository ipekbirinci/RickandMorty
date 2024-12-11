package com.example.rickandmorty.ui.bottomnavigation.episodes.data

import com.example.rickandmorty.ui.bottomnavigation.episodes.data.response.EpisodeResponse
import retrofit2.http.GET

interface EpisodesApiService {

    @GET("api/episode")
    fun getEpisodes(
    ): io.reactivex.rxjava3.core.Observable<EpisodeResponse>


}