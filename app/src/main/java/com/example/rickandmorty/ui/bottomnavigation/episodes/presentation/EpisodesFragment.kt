package com.example.rickandmorty.ui.bottomnavigation.episodes.presentation

import android.os.Bundle

import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodesBinding

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