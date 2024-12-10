package com.example.rickandmorty.ui.characterdetail.domain

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.characterdetail.data.repo.CharacterDetailRepository
import com.example.rickandmorty.ui.characterdetail.data.repo.CharacterDetailRepositoryImpl
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetCharacterDetail @Inject constructor(
    private val repository: CharacterDetailRepository,
) {
    data class Params(val id: Int)


    private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
    private val handlerScheduler = Schedulers.from { command -> mainThreadHandler.post(command) }

    val charactersResponseLiveData: MutableLiveData<Characters> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun execute(input:Params) {
        repository.getCharacterDetail(input.id)
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





