package com.example.rickandmorty.ui.bottomnavigation.settings.presentation

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
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
import com.example.rickandmorty.ui.MainActivity
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersFragmentDirections
import com.example.rickandmorty.ui.bottomnavigation.episodes.presentation.EpisodesAdapter
import com.example.rickandmorty.ui.bottomnavigation.episodes.presentation.EpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>(
    layoutId = R.layout.fragment_settings
) {
    private fun getCurrentActivity() = activity as? MainActivity

    override fun onInitDataBinding() {
        initClickListeners()
       if(getCurrentActivity()?.getIsEnglish() == true) {

           binding.icRight.setImageResource(R.drawable.eng)

       }else{
           binding.icRight.setImageResource(R.drawable.tr)

       }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initClickListeners(){
        binding.clickableFav.setOnClickListener{
            val action = SettingsFragmentDirections
                .actionSettingsFragment2ToFavoriteFragment()
            findNavController().navigate(action)
        }


        binding.changeLang.setOnClickListener {
            if (getCurrentActivity()?.getIsEnglish() == true) {
                binding.icRight.setImageResource(R.drawable.tr)
                getCurrentActivity()?.changeEnLanguage()
            } else {
                binding.icRight.setImageResource(R.drawable.eng)
                getCurrentActivity()?.changeTrLanguage()

            }
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