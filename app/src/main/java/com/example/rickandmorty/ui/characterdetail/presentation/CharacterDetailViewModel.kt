package com.example.rickandmorty.ui.characterdetail.presentation

import androidx.lifecycle.LiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.domain.GetCharacters
import com.example.rickandmorty.ui.characterdetail.domain.GetCharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetail: GetCharacterDetail
) :BaseViewModel(){

    val characterDetailResponse: LiveData<Characters> get() = getCharacterDetail.charactersResponseLiveData

    fun getCharacterDetail(id:Int) = getCharacterDetail.execute(GetCharacterDetail.Params(id))
}