package com.sleeplessknights.donence.ui.request

import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleeplessknights.donence.MainActivity
import com.sleeplessknights.donence.model.RequestResponse
import com.sleeplessknights.donence.rest.request.RequestRepository
import com.sleeplessknights.donence.ui.recyclepoints.RecyclePointsActivity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class RequestFragmentViewModel(
    private val requestRepository: RequestRepository
) : ViewModel() {

    private val isClicked = MutableLiveData<Boolean>()
    private val launchRecyclePointsActivity = MutableLiveData<Boolean>()

    fun onClicked() {
        isClicked.value = true
    }

    fun onShowRecyclePoints() {
        launchRecyclePointsActivity.value = true
    }

    fun getIsClicked(): LiveData<Boolean> {
        return isClicked
    }

    fun getLaunchRecyclePointsActivity(): LiveData<Boolean> {
        return launchRecyclePointsActivity
    }

    fun makeRequest(token: String, requestType: String) {
        viewModelScope.launch {
            val call = requestRepository.makeRequest(token, "requestType")
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