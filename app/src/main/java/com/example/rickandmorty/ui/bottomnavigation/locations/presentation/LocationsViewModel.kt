package com.example.rickandmorty.ui.bottomnavigation.locations.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.domain.GetCharacters
import com.example.rickandmorty.ui.bottomnavigation.locations.data.response.LocationResponse
import com.example.rickandmorty.ui.bottomnavigation.locations.domain.GetLocations
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class LocationsViewModel @Inject constructor(
    private val getLocations: GetLocations,
    private val getCharacters: GetCharacters
) :BaseViewModel(){
    fun getCharacters() = getCharacters.execute()

    val charactersResponse: LiveData<CharactersResponse> get() = getCharacters.charactersResponseLiveData


    val locationsResponse: LiveData<LocationResponse> get() = getLocations.charactersResponseLiveData

    fun getLocations() = getLocations.execute()
}