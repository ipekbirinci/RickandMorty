package com.example.rickandmorty.ui.bottomnavigation.characters.presantation


import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    layoutId = R.layout.fragment_characters
) {
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onInitDataBinding() {
        prepareRV()
        observeViewModel()

        binding.searchText.addTextChangedListener { text ->
            val query = text.toString()
            viewModel.searchCharacters(query)
        }
    }


    private fun prepareRV() {
        charactersAdapter =
            CharactersAdapter(requireContext(), object : CharactersClickListener {
                override fun characterItemClicked(model: Characters) {
                    val action = CharactersFragmentDirections
                        .actionCharactersFragmentToCharacterDetailFragment(model.id)
                    findNavController().navigate(action)
                }
            })
        binding.recyclerCharacter.run {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(context)
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun observeViewModel() {
        viewModel.charactersResponse.observe(viewLifecycleOwner, Observer { characters ->
                charactersAdapter.submitList(characters.results)

        })
        viewModel.filteredCharacters.observe(viewLifecycleOwner) { filteredList ->
            charactersAdapter.submitList(filteredList)
        }
        hideProgress()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProgress()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacters()
    }
}
