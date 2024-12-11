package com.example.rickandmorty.ui.bottomnavigation.episodes.data.response

data class EpisodeInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
