package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var noBackStack: Boolean = true
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.backGround)

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

    fun changeBackgroundColor(isWhite: Boolean? = false) {
        if (isWhite == true) {
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.backGround)
        } else {
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.backGroundItem)
        }
    }
}
