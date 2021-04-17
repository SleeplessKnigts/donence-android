package com.sleeplessknights.donence.ui.recyclepoints

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleeplessknights.donence.model.RecyclePoint
import com.sleeplessknights.donence.rest.recyclepoints.RecyclePointsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RecyclePointsViewModel(
    private val recyclePointsRepository: RecyclePointsRepository
) : ViewModel() {

    private val pointList = MutableLiveData<List<RecyclePoint>>()

    private fun getRecyclePoints(token: String) {
        viewModelScope.launch {
            val call = recyclePointsRepository.getRecyclePoints(token)
            call.enqueue(object : Callback, retrofit2.Callback<List<RecyclePoint>> {

                override fun onResponse(
                    call: Call<List<RecyclePoint>>,
                    response: Response<List<RecyclePoint>>
                ) {
                    if (response.isSuccessful)
                        setPointList(response.body())
                }

                override fun onFailure(call: Call<List<RecyclePoint>>, t: Throwable) {
                    print("fail")
                }

            })
        }
    }

    private fun setPointList(list: List<RecyclePoint>?) {
        pointList.value = list
    }

    fun getPointList(token: String): LiveData<List<RecyclePoint>> {
        getRecyclePoints(token)
        return pointList
    }
}