package com.example.rickandmorty.ui.bottomnavigation.characters.presantation

import GetCharacters
import com.example.rickandmorty.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters
) :BaseViewModel(){
    fun getCharacters() = getCharacters.execute(this, Unit)
}