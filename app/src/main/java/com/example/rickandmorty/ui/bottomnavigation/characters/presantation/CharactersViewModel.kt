package com.example.rickandmorty.ui.bottomnavigation.characters.presantation

import androidx.lifecycle.LiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.domain.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) :BaseViewModel(){
    val filteredCharacters = getCharacters.filteredCharactersLiveData


    fun searchCharacters(query: String) {
        getCharacters.search(query)
    }

    val charactersResponse: LiveData<CharactersResponse> get() = getCharacters.charactersResponseLiveData

    fun getCharacters() = getCharacters.execute()
}