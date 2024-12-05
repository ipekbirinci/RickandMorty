package com.example.rickandmorty.ui.bottomnavigation.characters.data.response

import com.google.gson.annotations.SerializedName
data class OriginResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
