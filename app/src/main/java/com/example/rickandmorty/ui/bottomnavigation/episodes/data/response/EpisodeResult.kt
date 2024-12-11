package com.example.rickandmorty.ui.bottomnavigation.episodes.data.response

data class EpisodeResult(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)

