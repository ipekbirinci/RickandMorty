package com.example.rickandmorty.ui.bottomnavigation.episodes.data.repo

import com.example.rickandmorty.ui.bottomnavigation.episodes.data.response.EpisodeResponse

interface EpisodesRepository {
    fun getEpisodes(): io.reactivex.rxjava3.core.Observable<EpisodeResponse>

}