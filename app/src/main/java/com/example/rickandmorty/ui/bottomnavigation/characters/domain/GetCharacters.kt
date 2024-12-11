package com.example.rickandmorty.ui.bottomnavigation.characters.domain

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
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

    @SuppressLint("CheckResult")
    fun execute() {
        repository.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(handlerScheduler)
            .subscribe({
                charactersResponseLiveData.value = it
                Log.d("GetCharacters", "Veri başarıyla alındı: $it")
            }, { error ->
                Log.e("GetCharacters", "Veri alınırken hata oluştu", error)
            })
    }
}


