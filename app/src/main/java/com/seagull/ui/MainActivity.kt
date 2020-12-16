package com.seagull.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.MaterialFadeThrough
import com.seagull.R
import com.seagull.misc.setupWithNavController

class MainActivity : AppCompatActivity() {
    private var currentNavController: LiveData<NavController>? = null

    companion object {
        //TODO move to Application
        lateinit var bottomNavigationView: BottomNavigationView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
        setupBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        val navGraphIds = listOf(
            R.navigation.daily_navigation,
            R.navigation.you_navigation,
            R.navigation.settings_navigation
        )
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragment_place,
            intent = intent
        )
        currentNavController = controller
    }


}