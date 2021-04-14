package com.sleeplessknights.donence.rest.profile

import com.sleeplessknights.donence.data.model.ProfileItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApiService {

    @GET("me")
    fun getProfile(@Header("Authorization") authorization:String) : Call<ProfileItem>

    companion object {
        private val URL = "http://10.0.2.2:8080/api/user/"

        fun create(): ProfileApiService {
            val retrofit
                    = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()

            return retrofit.create(ProfileApiService::class.java)
        }
    }
}