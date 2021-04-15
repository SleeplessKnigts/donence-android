package com.sleeplessknights.donence.rest.news

import com.sleeplessknights.donence.data.model.NewsItem
import com.sleeplessknights.donence.model.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface NewsApiService {

    @GET("news")
    fun getNews(@Header("Authorization") authorization:String) : Call<List<NewsResponse>>

    companion object {
        private val URL = "http://10.0.2.2:8080/api/user/"

        fun create(): NewsApiService {
            val retrofit
                    = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()

            return retrofit.create(NewsApiService::class.java)
        }
    }
}