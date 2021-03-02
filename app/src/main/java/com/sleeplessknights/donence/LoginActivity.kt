package com.sleeplessknights.donence

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.sleeplessknights.donence.base.viewModel
import com.sleeplessknights.donence.databinding.ActivityLoginBinding
import com.sleeplessknights.donence.rest.LoginRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModel { initViewModel() }

    private val RC_SIGN_IN = 9001 /* TODO: learn the purpose of this. seems like extremely random.*/
    private val LOG_TAG = "SignIn"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.vm = viewModel
        setContentView(binding.root)
        observe()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()

        googleSignInClient = GoogleSignIn.getClient(this, gso.build())
    }

    private fun observe() {
        viewModel.getIsClicked().observe(this, Observer { abc ->
            if (abc) {
                signIn()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            if (!account.isExpired) {
                viewModel.myLogin(account)
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, this.RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            checkExistence(account?.email)
            /* TODO: login success, switch to main activity and do not turn back until your login is expired
             *       if this is the first time, please give google the new user's address through maps api which will be in address activity and will play a key role in our application which is why we feed google with user's precious information probably without her/him even knowing it
             *      -Dz */

            viewModel.myLogin(account)

            Toast.makeText(applicationContext,
                "Welcome, " + account?.displayName,
                Toast.LENGTH_LONG)
                .also(Toast::show)

            /* this intent sends the user data to main activity.
             * try CTRL+SHIFT+F'ing for INTENT_LOGIN_MAIN */

        } catch (e: ApiException) {
            Log.w(LOG_TAG, "code: " + e.statusCode)
            /* TODO: login failed, HANDLE IT IF YOU CAN!
             *       (exclamation is for dramatic purposes, not anger nor rage or not trusting our little team of young developers)
             *      -Dz */
        }

    }

    /* TODO: implement. need my endpoint :(
    *       -Dz */
    private fun checkExistence(email: String?): Boolean {
        try {

        } catch (e: ApiException) {

        }

        return false
    }

    private fun initViewModel(): LoginActivityViewModel {
        val loginRepository = LoginRepository()
        val loginNavigator = LoginNavigator(this)
        return LoginActivityViewModel(loginRepository, loginNavigator)
    }

}

// DISCLAIMER (FOR NO REASON AT ALL):
// Function, class and package names, types, locations etc. are all subject to change.
// The goal is to provide basic functionality and understand how things work around here just for now.
// Happy messing around, boys!
// -Dz
