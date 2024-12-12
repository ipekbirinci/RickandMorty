package com.example.rickandmorty.ui

import android.annotation.SuppressLint
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
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var noBackStack: Boolean = true
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BaseViewModel
    private var isEnglish = true


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
    @SuppressLint("CommitPrefEdits")
    fun changeEnLanguage() {
        saveLanguagePreference("en")
        setAppLocale("en")
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isEnglish",true)
    }
    @SuppressLint("CommitPrefEdits")
    fun changeTrLanguage() {
        saveLanguagePreference("tr")
        setAppLocale("tr")
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isEnglish",false)
    }
    fun saveLanguagePreference(languageCode: String) {
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("language", languageCode)
        editor.apply()
    }
    fun getIsEnglish(): Boolean {
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isEnglish", true)
    }


    fun setAppLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }

    private fun changeBackgroundColor(isWhite: Boolean? = false) {
        if (isWhite == true) {
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.backGround)
        } else {
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.backGroundItem)
        }
    }
}
