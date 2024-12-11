package com.example.rickandmorty.ui.bottomnavigation.episodes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.domain.GetCharacters
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.response.EpisodeResponse
import com.example.rickandmorty.ui.bottomnavigation.episodes.domain.GetEpisodes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getEpisodes: GetEpisodes
) :BaseViewModel(){

    val episodesResponse: LiveData<EpisodeResponse> get() = getEpisodes.episodesResponseLiveData

    fun getEpisodes() = getEpisodes.execute()
}