package com.example.rickandmorty.ui.bottomnavigation.episodes.presentation

import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersAdapter
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersClickListener
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersFragmentDirections
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.repo.EpisodesRepository
import com.example.rickandmorty.ui.bottomnavigation.episodes.domain.GetEpisodes
import com.example.rickandmorty.ui.characterdetail.presentation.CharacterDetailFragmentArgs
import com.example.rickandmorty.ui.characterdetail.presentation.CharacterDetailFragmentDirections
import com.example.rickandmorty.ui.characterdetail.presentation.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
    layoutId = R.layout.fragment_episodes
) {
    private lateinit var episodesAdapter: EpisodesAdapter
    override fun onInitDataBinding() {
        prepareRV()
        observeViewModel()
    }

    private fun prepareRV() {
        episodesAdapter =
            EpisodesAdapter(requireContext())

        binding.episodesRV.run {
            adapter = episodesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun observeViewModel() {
        viewModel.episodesResponse.observe(viewLifecycleOwner, Observer { episodes ->
            episodesAdapter.submitList(episodes.results)


        })

        hideProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProgress()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getEpisodes()

    }
}