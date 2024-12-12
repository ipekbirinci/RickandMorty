package com.example.rickandmorty.ui.bottomnavigation.locations.data.datasource

import com.example.rickandmorty.ui.bottomnavigation.characters.data.CharactersApiService
import com.example.rickandmorty.ui.bottomnavigation.locations.data.LocationsApiService
import javax.inject.Inject

class LocationsDataSource @Inject constructor(
   private val apiService: LocationsApiService
) {
    fun getLocations() =
        apiService.getLocation()
}
