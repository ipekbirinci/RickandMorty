package com.example.rickandmorty.base



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
abstract class BaseViewModel : ViewModel() {
    companion object {
        const val EXEPTION_VALUE = 401
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    fun setLoading(loading: Boolean) {
        _loading.value = loading
    }



    var showProgress: MutableLiveData<Boolean> = MutableLiveData(false)

    open fun handleException(e: Exception) {
        when (e) {
            is HttpException -> {
                when (e.code()) {

                }
            }


        }

        if (e is HttpException) {
            when (e.code()) {

            }
        }
    }

}
