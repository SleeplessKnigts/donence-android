package com.sleeplessknights.donence.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.sleeplessknights.donence.model.User
import com.sleeplessknights.donence.rest.login.LoginRepository
import com.sleeplessknights.donence.ui.login.LoginNavigator
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivityViewModel(
    private val loginRepository: LoginRepository,
    private val navigator: LoginNavigator
) : ViewModel() {

    private val isClicked = MutableLiveData<Boolean>()

    fun onClicked() {
        isClicked.value = true
    }

    fun getIsClicked(): LiveData<Boolean> {
        return isClicked
    }

    fun myLogin(account: GoogleSignInAccount?) {
        viewModelScope.launch {
            val call = loginRepository.userAuth(account)
            call.enqueue(object : Callback, retrofit2.Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        navigator.navigateToMain()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {

                }

            })
        }
    }
}