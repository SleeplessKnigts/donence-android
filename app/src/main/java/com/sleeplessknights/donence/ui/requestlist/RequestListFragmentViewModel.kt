package com.sleeplessknights.donence.ui.requestlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleeplessknights.donence.model.RequestResponse
import com.sleeplessknights.donence.rest.requestlist.RequestListRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RequestListFragmentViewModel(
    private val requestListRepository: RequestListRepository
) : ViewModel() {

    private val requestList = MutableLiveData<ArrayList<RequestResponse>>()

    fun getData(token: String): LiveData<ArrayList<RequestResponse>> {
        getRequestList(token)
        return requestList
    }

    fun getRequestList(token: String) {
        viewModelScope.launch {
            val callActive = requestListRepository.makeRequestForActive(token)
            val callOld = requestListRepository.makeRequestForOld(token)
            val tempList: ArrayList<RequestResponse> = ArrayList()

            callActive.enqueue(object : Callback, retrofit2.Callback<List<RequestResponse>> {
                override fun onResponse(
                    call: Call<List<RequestResponse>>,
                    response: Response<List<RequestResponse>>
                ) {
                    if (response.isSuccessful) {
                        onActiveListSuccess(response.body() as ArrayList<RequestResponse>?, tempList)
                    }
                }

                override fun onFailure(call: Call<List<RequestResponse>>, t: Throwable) {
                    t.printStackTrace()
                    call.toString()
                    onRequestFailure()
                }
            })

            callOld.enqueue(object : Callback, retrofit2.Callback<List<RequestResponse>> {
                override fun onResponse(
                    call: Call<List<RequestResponse>>,
                    response: Response<List<RequestResponse>>
                ) {
                    if (response.isSuccessful) {
                        onOldListSuccess(response.body() as ArrayList<RequestResponse>?, tempList)
                    }
                }

                override fun onFailure(call: Call<List<RequestResponse>>, t: Throwable) {
                    t.printStackTrace()
                    call.toString()
                    onRequestFailure()
                }
            })
        }
    }

    private fun onOldListSuccess(
        requests: ArrayList<RequestResponse>?,
        tempList: ArrayList<RequestResponse>
    ) {
        if (requests != null) {
            tempList.addAll(requests)
            requestList.value = tempList
        }
    }

    private fun onRequestFailure() {
        TODO("Not yet implemented")
    }

    private fun onActiveListSuccess(
        requests: ArrayList<RequestResponse>?,
        tempList: ArrayList<RequestResponse>
    ) {
        if (requests != null) {
            tempList.addAll(requests)
            requestList.value = tempList
        }
    }
}