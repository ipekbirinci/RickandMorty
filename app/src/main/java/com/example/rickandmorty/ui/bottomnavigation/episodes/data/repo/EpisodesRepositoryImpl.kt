package com.example.rickandmorty.ui.bottomnavigation.episodes.data.repo

import com.example.rickandmorty.ui.bottomnavigation.episodes.data.datasource.EpisodesDataSource
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(private val dataSource: EpisodesDataSource):
    EpisodesRepository {
    override fun getEpisodes() = dataSource.getEpisodes()

}