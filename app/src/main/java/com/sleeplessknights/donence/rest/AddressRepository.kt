package com.sleeplessknights.donence.rest

import com.sleeplessknights.donence.AddressLiveData
import com.sleeplessknights.donence.data.addressData
import com.sleeplessknights.donence.data.model.AddressItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class AddressRepository() {

    private val adressApiService by lazy {
        AddressApiService.create()
    }

    suspend fun setAddress(address: AddressLiveData): Call<AddressItem> {

        return withContext(Dispatchers.IO) {
            /* backend struggle starts here */
            return@withContext adressApiService.submitAddress(
            addressData(address.value!!.latitude, address.value!!.longitude))

             // !! don't forget TODO: get rid of !!s
        }
    }
}
