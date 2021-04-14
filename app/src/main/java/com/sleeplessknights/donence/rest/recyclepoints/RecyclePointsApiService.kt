package com.sleeplessknights.donence.rest.recyclepoints

import com.sleeplessknights.donence.model.LoginResponse
import com.sleeplessknights.donence.model.RecyclePoint
import com.sleeplessknights.donence.rest.login.UserApiService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface RecyclePointsApiService {
    @GET("recycle-point")
    fun getRecyclePoints(@Header("Authorization") authorization: String): Call<List<RecyclePoint>>


    companion object {
        private val URL = "http://10.0.2.2:8080/api/user/"

        fun create(): RecyclePointsApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(RecyclePointsApiService::class.java)
        }
    }
}