package com.sleeplessknights.donence

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.sleeplessknights.donence.model.User
import com.sleeplessknights.donence.rest.LoginRepository
import com.sleeplessknights.donence.rest.UserApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001 /* TODO: learn the purpose of this. seems like extremely random.*/
    private val LOG_TAG = "SignIn"
    private val loginRepository: LoginRepository = LoginRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var button: SignInButton = findViewById(R.id.sign_in_button)
        button.setOnClickListener{
            onClick(button)
        }
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()

        googleSignInClient = GoogleSignIn.getClient(this, gso.build())
    }

    override fun onStart() {
        super.onStart()
        var account = GoogleSignIn.getLastSignedInAccount(this)

        var mainIntent = Intent(this, MainActivity::class.java)
        if (account != null) {
            if (!account.isExpired) {
                mainIntent.putExtra("loggedIn", true)
                mainIntent.putExtra("userId", account.id)
                mainIntent.putExtra("userEmail", account.email)
                mainIntent.putExtra("userDisplayName", account.displayName)
                startActivity(mainIntent)
            }
        }
    }

    fun onClick(view: View) {
        if (view.id == R.id.sign_in_button) {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, this.RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == this.RC_SIGN_IN) {
            var task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            var account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            checkExistence(account?.email)
            /* TODO: login success, switch to main activity and do not turn back until your login is expired
             *       if this is the first time, please give google the new user's address through maps api which will be in address activity and will play a key role in our application which is why we feed google with user's precious information probably without her/him even knowing it
             *      -Dz */
            Toast.makeText(applicationContext, "Welcome, " + account?.displayName, Toast.LENGTH_LONG)
                    .also(Toast::show)

            /* this intent sends the user data to main activity.
             * try CTRL+SHIFT+F'ing for INTENT_LOGIN_MAIN */
            var mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
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

        } catch (e: ApiException){

        }

        return false
    }

    // BEWARE: This function executes coroutines.
    //         See https://developer.android.com/kotlin/coroutines for more information.
    //        -Dz
    private fun login(account: GoogleSignInAccount?) {
        //this.loginRepository.userAuth(account)
    }
}

// DISCLAIMER (FOR NO REASON AT ALL):
// Function, class and package names, types, locations etc. are all subject to change.
// The goal is to provide basic functionality and understand how things work around here just for now.
// Happy messing around, boys!
// -Dz
