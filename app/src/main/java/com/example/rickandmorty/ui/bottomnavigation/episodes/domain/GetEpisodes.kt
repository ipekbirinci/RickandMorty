package com.example.rickandmorty.ui.bottomnavigation.episodes.domain

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersViewModel
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.repo.EpisodesRepository
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.response.EpisodeResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetEpisodes @Inject constructor(
    private val repository: EpisodesRepository,
) {

    private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
    private val handlerScheduler = Schedulers.from { command -> mainThreadHandler.post(command) }

    val episodesResponseLiveData: MutableLiveData<EpisodeResponse> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun execute() {
        repository.getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(handlerScheduler)
            .subscribe({
                episodesResponseLiveData.value = it
                Log.d("GetCharacters", "Veri başarıyla alındı: $it")
            }, { error ->
                Log.e("GetCharacters", "Veri alınırken hata oluştu", error)
            })
    }
}


