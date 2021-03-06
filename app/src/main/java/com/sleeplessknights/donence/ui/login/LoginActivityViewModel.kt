package com.sleeplessknights.donence.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.sleeplessknights.donence.model.LoginResponse
import com.sleeplessknights.donence.rest.login.LoginRepository
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
            call.enqueue(object : Callback, retrofit2.Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        navigator.navigateToMain(response.body())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                }

            })
        }
    }
}