package com.sleeplessknights.donence.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sleeplessknights.donence.LoginActivity
import com.sleeplessknights.donence.MainActivity
import java.lang.ref.WeakReference

class LoginNavigator(private val activity: LoginActivity) {

    private val activityReference = WeakReference<AppCompatActivity>(activity)

    fun navigateToMain() {
        activityReference.get()?.apply {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }

}
