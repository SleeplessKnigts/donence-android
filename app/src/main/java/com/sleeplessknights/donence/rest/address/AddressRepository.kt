package com.sleeplessknights.donence.rest.address

import com.sleeplessknights.donence.data.AddressData
import com.sleeplessknights.donence.data.model.AddressItem
import com.sleeplessknights.donence.ui.address.AddressLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class AddressRepository() {

    private val adressApiService by lazy {
        AddressApiService.create()
    }

    suspend fun setAddress(headerToken: String, address: AddressLiveData): Call<Void> {

        return withContext(Dispatchers.IO) {
            /* backend struggle starts here */
            val son = ("Bearer ".plus(headerToken))
            return@withContext adressApiService.submitAddress(son,
                AddressData(address.value!!.latitude, address.value!!.longitude))

            // !! don't forget TODO: get rid of !!s
        }
    }
}
