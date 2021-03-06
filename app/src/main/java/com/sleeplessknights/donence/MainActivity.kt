package com.sleeplessknights.donence

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_request))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    /* TODO: remove these toys */
    fun placeHolderOnClick(view: View) {
        val toastText = "Clicked the button!"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, toastText, duration)
        toast.show()
    }
}

// DISCLAIMER (FOR NO REASON AT ALL):
// Function, class and package names, types, locations etc. are all subject to change.
// The goal is to provide basic functionality and understand how things work around here just for now.
// Happy developing, boys!
// -Dz