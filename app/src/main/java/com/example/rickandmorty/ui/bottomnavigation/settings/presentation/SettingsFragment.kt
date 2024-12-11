package com.example.rickandmorty.ui.bottomnavigation.settings.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.databinding.FragmentSettingsBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersFragmentDirections
import com.example.rickandmorty.ui.bottomnavigation.episodes.presentation.EpisodesAdapter
import com.example.rickandmorty.ui.bottomnavigation.episodes.presentation.EpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>(
    layoutId = R.layout.fragment_settings
) {
    override fun onInitDataBinding() {
        initClickListeners()
    }

    private fun initClickListeners(){
        binding.clickableFav.setOnClickListener{
            val action = SettingsFragmentDirections
                .actionSettingsFragment2ToFavoriteFragment()
            findNavController().navigate(action)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  showProgress()

    }

    override fun onResume() {
        super.onResume()

    }
}