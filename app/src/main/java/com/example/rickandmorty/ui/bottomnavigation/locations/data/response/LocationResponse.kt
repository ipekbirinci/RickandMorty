package com.example.rickandmorty.ui.bottomnavigation.locations.data.response

import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.InfoResponse

data class LocationResponse(
    val info: InfoResponse,
    val results: List<Locations>
)
