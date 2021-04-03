package com.sleeplessknights.donence.ui.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleeplessknights.donence.model.RequestResponse
import com.sleeplessknights.donence.rest.request.RequestRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RequestFragmentViewModel(
    private val requestRepository: RequestRepository
) : ViewModel() {

    private val isClicked = MutableLiveData<Boolean>()

    fun onClicked() {
        isClicked.value = true
    }

    fun getIsClicked(): LiveData<Boolean> {
        return isClicked
    }

    fun makeRequest(requestType: String) {
        viewModelScope.launch {
            val call = requestRepository.makeRequest(requestType)
            call.enqueue(object : Callback, retrofit2.Callback<RequestResponse> {
                override fun onResponse(
                    call: Call<RequestResponse>,
                    response: Response<RequestResponse>
                ) {
                    if (response.isSuccessful) {
                        onRequestSuccess()
                    }
                }

                override fun onFailure(call: Call<RequestResponse>, t: Throwable) {
                    onRequestFailure()
                }
            })
        }
    }

    private fun onRequestFailure() {
        TODO("Not yet implemented")
    }

    private fun onRequestSuccess() {
        TODO("Not yet implemented")
    }

}