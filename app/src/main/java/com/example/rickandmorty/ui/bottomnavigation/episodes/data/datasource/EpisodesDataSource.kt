package com.example.rickandmorty.ui.bottomnavigation.episodes.data.datasource

import com.example.rickandmorty.ui.bottomnavigation.episodes.data.EpisodesApiService
import javax.inject.Inject


class EpisodesDataSource @Inject constructor(
    private val apiService: EpisodesApiService
) {
    fun getEpisodes() =
        apiService.getEpisodes()
}