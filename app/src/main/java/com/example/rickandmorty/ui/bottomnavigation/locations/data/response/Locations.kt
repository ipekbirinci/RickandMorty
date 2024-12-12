package com.example.rickandmorty.ui.bottomnavigation.locations.data.response

data class Locations(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)