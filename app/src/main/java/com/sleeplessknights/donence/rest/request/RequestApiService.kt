package com.sleeplessknights.donence.rest.request

import com.sleeplessknights.donence.model.Request
import com.sleeplessknights.donence.model.RequestResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestApiService {
    @POST("request")
    fun request(@Body request: Request): Call<RequestResponse>

    companion object {
        private val URL = "http://10.0.2.2:8080/api/requests/"

        fun create(): RequestApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(RequestApiService::class.java)
        }
    }
}