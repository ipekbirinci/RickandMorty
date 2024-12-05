package com.example.rickandmorty.ui.bottomnavigation.characters.presantation


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
    }


    private fun prepareRV() {
        charactersAdapter =
            CharactersAdapter(requireContext(), object : CharactersClickListener {
                override fun characterItemClicked(model: Characters) {
                    // Detay sayfasına geçiş yapılabilir
                }
            })
        binding.recyclerCharacter.run {
            adapter = charactersAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun observeViewModel() {
        viewModel.getCharacters().observe(viewLifecycleOwner) { response ->
            response.forEach { charactersResponse ->
                charactersAdapter.submitList(charactersResponse.results)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacters()
    }
}
