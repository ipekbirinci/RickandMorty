package com.example.rickandmorty.ui.bottomnavigation.characters.presantation

import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters

interface CharactersClickListener {
    fun characterItemClicked(model: Characters)
}