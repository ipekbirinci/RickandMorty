package com.example.rickandmorty.ui.bottomnavigation.locations.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.domain.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class LocationsViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) :BaseViewModel(){

    val charactersResponse: LiveData<CharactersResponse> get() = getCharacters.charactersResponseLiveData

    fun getCharacters() = getCharacters.execute()
}