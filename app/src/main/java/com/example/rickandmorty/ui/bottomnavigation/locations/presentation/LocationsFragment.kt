package com.example.rickandmorty.ui.bottomnavigation.locations.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.Characters
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersClickListener
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding, LocationsViewModel>(
    layoutId = R.layout.fragment_locations
) {
    private lateinit var locationsAdapter: LocationsAdapter
    private var locationList = arrayListOf<String>()
    private var dimensionList = arrayListOf<String>()
    private var typeList = arrayListOf<String>()
    private var createdList = arrayListOf<String>()


    override fun onInitDataBinding() {
        prepareRV()
        observeViewModel()
        initClickListeners()
    }


    private fun initClickListeners(){
        binding.run {
            linear1.setOnClickListener {
                showLocationDialog()
            }

            linear2.setOnClickListener {
                showTypeDialog()
            }

            linear3.setOnClickListener {
                showDimensionDialog()
            }

            linear4.setOnClickListener {
                showCreatedDialog()
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun showTypeDialog(){
        MaterialDialog(requireContext()).show {
            title(text = getString(R.string.types))
            view.setBackgroundColor(Color.parseColor("#0c7d85"))
            listItems(items = typeList) { _, _, a ->
                binding.run {
                    typeValue.text = a.toString()
                    typeValue.setTextColor(Color.WHITE)
                }
            }
        }

    }
    @SuppressLint("CheckResult")
    private fun showLocationDialog(){
        MaterialDialog(requireContext()).show {
            title(text = getString(R.string.locations))
            view.setBackgroundColor(Color.parseColor("#0c7d85"))
            listItems(items = locationList) { _, _, a ->
                binding.run {
                    valueText.text = a.toString()
                    valueText.setTextColor(Color.WHITE)
                }
            }
        }

    }
    @SuppressLint("CheckResult")
    private fun showCreatedDialog(){
        MaterialDialog(requireContext()).show {
            title(text = getString(R.string.locations))
            view.setBackgroundColor(Color.parseColor("#0c7d85"))
            listItems(items = createdList) { _, _, a ->
                binding.run {
                    createdValue.text = a.toString()
                    createdValue.setTextColor(Color.WHITE)
                }
            }
        }


    }
    @SuppressLint("CheckResult")
    private fun showDimensionDialog(){
        MaterialDialog(requireContext()).show {
            title(text = getString(R.string.locations))
            view.setBackgroundColor(Color.parseColor("#0c7d85"))
            listItems(items = dimensionList) { _, _, a ->
                binding.run {
                    dimensionValue.text = a.toString()
                    dimensionValue.setTextColor(Color.WHITE)
                }
            }
        }
    }

    private fun getLocationsAndAdd(){

        viewModel.locationsResponse.observe(viewLifecycleOwner, Observer { locations ->
          typeList= arrayListOf()
            locationList= arrayListOf()
            dimensionList= arrayListOf()
            createdList= arrayListOf()
                locations.results.forEach{
                    it.type.let { it1 -> typeList.add(it1) }
                    it.created.let{it1 -> createdList.add(it1)}
                    it.dimension.let{it1 -> dimensionList.add(it1)}

                    it.residents.let { it1 ->
                        val trimmedList = it1.map { url ->
                            url.substringAfterLast("/")
                        }
                        locationList.addAll(trimmedList)
                    }


                }

        })

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
        getLocationsAndAdd()
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
        viewModel.getLocations()
    }
}
