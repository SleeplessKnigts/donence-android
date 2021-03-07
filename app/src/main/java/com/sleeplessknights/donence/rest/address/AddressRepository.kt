package com.sleeplessknights.donence.rest.address

import com.sleeplessknights.donence.ui.address.AddressLiveData
import com.sleeplessknights.donence.data.addressData
import com.sleeplessknights.donence.data.model.AddressItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class AddressRepository() {

    private val adressApiService by lazy {
        AddressApiService.create()
    }

    suspend fun setAddress(headerToken:String, address: AddressLiveData): Call<AddressItem> {

        return withContext(Dispatchers.IO) {
            /* backend struggle starts here */
            val son = ("Bearer ".plus(headerToken))
            return@withContext adressApiService.submitAddress(son,
            addressData(address.value!!.latitude, address.value!!.longitude))

             // !! don't forget TODO: get rid of !!s
        }
    }
}
