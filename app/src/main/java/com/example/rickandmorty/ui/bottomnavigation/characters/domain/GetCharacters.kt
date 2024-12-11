package com.example.rickandmorty.ui.bottomnavigation.characters.domain

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetCharacters @Inject constructor(
    private val repository: CharactersRepository,
) {

    private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
    private val handlerScheduler = Schedulers.from { command -> mainThreadHandler.post(command) }

    val charactersResponseLiveData: MutableLiveData<CharactersResponse> = MutableLiveData()
    val filteredCharactersLiveData: MutableLiveData<List<Characters>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun execute() {
        repository.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(handlerScheduler)
            .subscribe({ response ->
                charactersResponseLiveData.value = response
                filteredCharactersLiveData.value = response.results // Tüm karakterler başlangıçta
            }, { error ->
            })
    }

    fun search(query: String) {
        val currentResponse = charactersResponseLiveData.value
        if (currentResponse != null) {
            val filteredResults = currentResponse.results.filter { character ->
                character.name.contains(query, ignoreCase = true)
            }
            filteredCharactersLiveData.value = filteredResults
        }
    }
}
