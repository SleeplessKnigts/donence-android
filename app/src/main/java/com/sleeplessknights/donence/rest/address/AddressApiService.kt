package com.sleeplessknights.donence.rest.address
import com.sleeplessknights.donence.data.AddressData
import com.sleeplessknights.donence.data.model.AddressItem

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AddressApiService {

    @POST("address")
    fun submitAddress(@Header("Authorization") authorization:String, @Body request: AddressData): Call<Void>

    companion object {
        private val URL = "https://donence.herokuapp.com/api/user/"

        fun create(): AddressApiService {
            val retrofit
            = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()

            return retrofit.create(AddressApiService::class.java)
        }
    }
}