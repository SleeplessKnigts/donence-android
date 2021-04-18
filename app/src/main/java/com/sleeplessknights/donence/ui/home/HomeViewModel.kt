package com.sleeplessknights.donence.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleeplessknights.donence.data.model.NewsItem
import com.sleeplessknights.donence.model.NewsResponse
import com.sleeplessknights.donence.rest.news.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDateTime
import javax.security.auth.callback.Callback

class HomeViewModel : ViewModel() {

    val headings: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val contents: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val imageUrls: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val createdAts: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val genel: MutableLiveData<List<NewsResponse>> by lazy {
        MutableLiveData<List<NewsResponse>>()
    }
    val newsRepository: NewsRepository
        get() = NewsRepository()


    fun getNews(token: String) {
        viewModelScope.launch {

            val call = newsRepository.getNews(token)
            call.enqueue(object : Callback, retrofit2.Callback<List<NewsResponse>> {
                override fun onResponse(call: Call<List<NewsResponse>>, response: Response<List<NewsResponse>>) {
                    if (response.isSuccessful) {
                        Log.d("TAG", "Address COMING FROM BASE")

                        genel.postValue(response.body())
                        genel.postValue(response.body())

                        Log.d("TAG", "hi")
                    }
                }

                override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {

                }
            })
        }
    }

}