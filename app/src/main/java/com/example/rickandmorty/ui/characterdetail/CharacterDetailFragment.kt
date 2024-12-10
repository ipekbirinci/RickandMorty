package com.example.rickandmorty.ui.characterdetail

import android.os.Bundle

import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
    layoutId = R.layout.fragment_character_detail
) {
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var characterId: String
    override fun onInitDataBinding() {
        prepareRV()
        characterId = arguments?.getString("characterId") ?: ""

        observeViewModel()
    }


    private fun prepareRV() {

    }

    override fun observeViewModel() {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProgress()

    }

    override fun onResume() {
        super.onResume()

    }
}
