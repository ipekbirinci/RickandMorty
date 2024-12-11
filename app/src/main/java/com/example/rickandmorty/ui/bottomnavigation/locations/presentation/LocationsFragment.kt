package com.example.rickandmorty.ui.bottomnavigation.locations.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersAdapter
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersClickListener
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersFragmentDirections
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    layoutId = R.layout.fragment_locations
) {
    private lateinit var locationsAdapter: LocationsAdapter

    override fun onInitDataBinding() {
        prepareRV()
        observeViewModel()
    }


    private fun prepareRV() {
        locationsAdapter =
            LocationsAdapter(requireContext(), object : CharactersClickListener {
                override fun characterItemClicked(model: Characters) {
                    val action = CharactersFragmentDirections
                        .actionCharactersFragmentToCharacterDetailFragment(model.id)
                    findNavController().navigate(action)
                }
            })

        binding.locationsRv.run {
            adapter = locationsAdapter
            layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun observeViewModel() {
        viewModel.charactersResponse.observe(viewLifecycleOwner, Observer { characters ->
            locationsAdapter.submitList(characters.results)

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacters()
    }
}
