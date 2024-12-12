package com.example.rickandmorty.ui.bottomnavigation.locations.data

import com.example.rickandmorty.ui.bottomnavigation.locations.data.response.LocationResponse
import retrofit2.http.GET

interface LocationsApiService {
    @GET("api/location")
    fun getLocation(
    ): io.reactivex.rxjava3.core.Observable<LocationResponse>

}
