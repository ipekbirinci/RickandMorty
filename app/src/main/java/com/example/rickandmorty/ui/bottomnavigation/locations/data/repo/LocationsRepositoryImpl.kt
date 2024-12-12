package com.example.rickandmorty.ui.bottomnavigation.locations.data.repo

import com.example.rickandmorty.ui.bottomnavigation.characters.data.datasource.CharactersDataSource
import com.example.rickandmorty.ui.bottomnavigation.locations.data.datasource.LocationsDataSource
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(private val dataSource: LocationsDataSource):LocationsRepository{
    override fun getLocations() = dataSource.getLocations()

}