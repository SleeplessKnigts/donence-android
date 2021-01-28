package com.sleeplessknights.donence

import android.content.Intent
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
    private var loggedIn: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_dropoff))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        /* TODO: i don't even know what is to be done here but surely there is or will be something.
         *       so... there is that.
         *      -Dz */

        /* this intent kindly accepts the user data from login activity.
         * try CTRL+SHIFT+F'ing for INTENT_LOGIN_MAIN */
        loggedIn = intent.getBooleanExtra("loggedIn", false)
        if(!loggedIn) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
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