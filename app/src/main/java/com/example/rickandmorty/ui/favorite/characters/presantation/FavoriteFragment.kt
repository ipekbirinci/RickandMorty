package com.example.rickandmorty.ui.favorite.characters.presantation

import android.os.Bundle
import androidx.navigation.fragment.findNavController

import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentFavoriteBinding
import com.example.rickandmorty.ui.bottomnavigation.settings.presentation.SettingsFragmentDirections

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    layoutId = R.layout.fragment_favorite
) {
    override fun onInitDataBinding() {
        prepareRV()
        initClickListeners()
        observeViewModel()
    }

    private fun initClickListeners(){

            binding.back.setOnClickListener{
                val action = FavoriteFragmentDirections
                    .actionFavoriteFragmentToSettingsFragment2()
                findNavController().navigate(action)
            }


    }
    private fun prepareRV() {

    }

    override fun observeViewModel() {

        hideProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProgress()

    }

    override fun onResume() {
        super.onResume()


    }
}
