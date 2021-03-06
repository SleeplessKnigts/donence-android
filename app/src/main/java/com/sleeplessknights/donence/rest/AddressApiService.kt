package com.sleeplessknights.donence.rest

import com.sleeplessknights.donence.data.deneme
import com.sleeplessknights.donence.data.model.AddressItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

interface AddressApiService {

    @POST("address")
    fun submitAddress(@Body request: deneme): Call<AddressItem>

    companion object {
        private val URL = "http://10.0.2.2:8080/api/user/" /*TODO: fun fact: 10.0.2.2 refers to host loopback ip address in avd.
                                                  *       replace with real url _when the time comes_.
                                                  *      -Dz */
        fun create(): AddressApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(AddressApiService::class.java)
        }
    }
}
