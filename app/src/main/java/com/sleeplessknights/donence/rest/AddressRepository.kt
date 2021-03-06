package com.sleeplessknights.donence.rest

import com.sleeplessknights.donence.AddressLiveData
import com.sleeplessknights.donence.data.deneme
import com.sleeplessknights.donence.data.model.AddressItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class AddressRepository() {

    private val userApiService by lazy {
        UserApiService.create()
    }

    suspend fun setAddress(address: AddressLiveData): Call<AddressItem> {
        return withContext(Dispatchers.IO) {
            /* backend struggle starts here */
            return@withContext userApiService.authenticateUser(
                deneme(address.value!!.latitude, address.value!!.longitude))  // !! don't forget TODO: get rid of !!s
        }
    }

}
