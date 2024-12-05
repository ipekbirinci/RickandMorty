package com.example.rickandmorty.ui.bottomnavigation.characters.data.response
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info") val info: InfoResponse,
    @SerializedName("results") val results: List<Characters>
)


