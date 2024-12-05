package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var noBackStack: Boolean = true
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.backGround)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeBackgroundColor()
        supportActionBar?.hide()

        intent.extras?.getInt("destination")?.let {
            openNavViewWithDestination(it)
        } ?: run {
            openNavViewWithDestination()
        }
    }

    private fun openNavViewWithDestination(destination: Int? = null) {
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        navView.setupWithNavController(navController)

        destination?.let {
            navView.selectedItemId = destination
        }

        navHostFragment.childFragmentManager.addOnBackStackChangedListener {
            noBackStack = navHostFragment.childFragmentManager.backStackEntryCount == 0
        }
    }

    private fun changeBackgroundColor(isWhite: Boolean? = false) {
        if (isWhite == true) {
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.backGround)
        } else {
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.backGroundItem)
        }
    }
}
