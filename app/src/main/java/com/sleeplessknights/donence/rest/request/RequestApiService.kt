package com.sleeplessknights.donence.rest.request

import com.sleeplessknights.donence.model.Request
import com.sleeplessknights.donence.model.RequestResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RequestApiService {
    @POST("request/")
    fun request(@Header("Authorization") authorization: String, @Body request: Request): Call<RequestResponse>

    companion object {
        private val URL = "https://donence.herokuapp.com/api/"

        fun create(): RequestApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(RequestApiService::class.java)
        }
    }
}