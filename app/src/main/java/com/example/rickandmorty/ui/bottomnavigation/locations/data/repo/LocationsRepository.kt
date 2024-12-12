package com.example.rickandmorty.ui.bottomnavigation.locations.data.repo

import android.database.Observable
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.locations.data.response.LocationResponse

interface LocationsRepository {
    fun getLocations(): io.reactivex.rxjava3.core.Observable<LocationResponse>

}