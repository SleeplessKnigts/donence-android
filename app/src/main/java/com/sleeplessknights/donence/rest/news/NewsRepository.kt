package com.sleeplessknights.donence.rest.news

import com.sleeplessknights.donence.data.model.NewsItem
import com.sleeplessknights.donence.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class NewsRepository() {

    private val newsApiService by lazy {
        NewsApiService.create()
    }

    suspend fun getNews(headerToken: String) : Call<List<NewsResponse>> {

        return withContext(Dispatchers.IO) {
            /* backend struggle starts here */
            val son = ("Bearer ".plus(headerToken))
            return@withContext newsApiService.getNews(son)
        }
    }
}