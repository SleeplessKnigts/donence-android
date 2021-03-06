package com.sleeplessknights.donence.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sleeplessknights.donence.LoginActivity
import com.sleeplessknights.donence.MainActivity
import com.sleeplessknights.donence.base.putAny
import com.sleeplessknights.donence.model.LoginResponse
import java.lang.ref.WeakReference

class LoginNavigator(private val activity: LoginActivity) {

    private val activityReference = WeakReference<AppCompatActivity>(activity)

    fun navigateToMain(body: LoginResponse?) {
        activityReference.get()?.apply {
            val mainIntent = Intent(this, MainActivity::class.java)
            getSharedPreferences(packageName, Context.MODE_PRIVATE).putAny("user_responseBody", body)
            startActivity(mainIntent)
        }
    }

}
