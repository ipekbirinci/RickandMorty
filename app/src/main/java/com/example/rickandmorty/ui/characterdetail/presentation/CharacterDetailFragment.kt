package com.example.rickandmorty.ui.characterdetail.presentation

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

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
    private  var characterId: Int=0
    override fun onInitDataBinding() {
        val args = CharacterDetailFragmentArgs.fromBundle(requireArguments())
        characterId = args.id
        observeViewModel()
        initClickListeners()
    }

    fun initClickListeners(){
        binding.back.setOnClickListener {
            val action = CharacterDetailFragmentDirections
                .actionCharacterDetailFragmentToCharactersFragment()
            findNavController().navigate(action)
        }
    }

    override fun observeViewModel() {
        viewModel.characterDetailResponse.observe(viewLifecycleOwner, Observer { characters ->

            binding.run {
                nameOfCharacter.text=characters.name
                val placeholderColor =
                    ColorDrawable(ContextCompat.getColor(characterImg.context, R.color.lines))
                Glide.with(characterImg.context).load(characters.image)
                    .placeholder(placeholderColor)
                    .transform(CenterCrop(), RoundedCorners(50)).into(characterImg)

                statusValue.text=characters.status
                speciesValue.text=characters.species
                typeValue.text=characters.type
                locationValue.text= characters.location.toString()
                episodeValue.text = characters.episode.joinToString(", ") {
                    it.substringAfterLast("/")
                }

                createdValue.text=characters.created
            }


        })

        hideProgress()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProgress()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacterDetail(characterId)

    }
}
