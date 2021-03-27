package com.sleeplessknights.donence.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.sleeplessknights.donence.data.model.ProfileItem
import com.sleeplessknights.donence.rest.address.ProfileRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProfileViewModel : ViewModel() {

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val fname: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val imageUrl: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val lat: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val latLng: MutableLiveData<LatLng> by lazy {
        MutableLiveData<LatLng>()
    }

    val profileRepository: ProfileRepository
        get() = ProfileRepository()

    fun getDetails(token: String) {
        viewModelScope.launch {

            val call = profileRepository.getDetails(token)
            call.enqueue(object : Callback, retrofit2.Callback<ProfileItem> {
                override fun onResponse(call: Call<ProfileItem>, response: Response<ProfileItem>) {
                    if (response.isSuccessful) {
                        Log.d("TAG", "Address COMING FROM BASE")

                        email.value = response.body()?.email.toString()
                        fname.value = response.body()?.fname.toString()
                        imageUrl.value = response.body()?.imageUrl.toString()
                        latLng.value = LatLng(response.body()!!.lng, response.body()!!.lat)
                    }
                }

                override fun onFailure(call: Call<ProfileItem>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}