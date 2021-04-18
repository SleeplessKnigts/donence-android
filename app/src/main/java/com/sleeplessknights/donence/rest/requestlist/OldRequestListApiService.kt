package com.sleeplessknights.donence.rest.requestlist

import com.sleeplessknights.donence.model.RequestListResponse
import com.sleeplessknights.donence.model.RequestResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface OldRequestListApiService {
    @GET("completed")
    fun request(@Header("Authorization") authorization: String): Call<List<RequestResponse>>

    companion object {
        private val URL = "https://donence.herokuapp.com/api/user/requests/"

        fun create(): OldRequestListApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(OldRequestListApiService::class.java)
        }
    }
}