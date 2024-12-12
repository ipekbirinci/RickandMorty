package com.example.rickandmorty.ui.bottomnavigation.locations.domain

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
import com.example.rickandmorty.ui.bottomnavigation.locations.data.repo.LocationsRepository
import com.example.rickandmorty.ui.bottomnavigation.locations.data.response.LocationResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetLocations @Inject constructor(
    private val repository: LocationsRepository,
) {

    private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
    private val handlerScheduler = Schedulers.from { command -> mainThreadHandler.post(command) }

    val charactersResponseLiveData: MutableLiveData<LocationResponse> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun execute() {
        repository.getLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(handlerScheduler)
            .subscribe({ response ->
                charactersResponseLiveData.value = response
            }, { error ->
            })
    }

}
