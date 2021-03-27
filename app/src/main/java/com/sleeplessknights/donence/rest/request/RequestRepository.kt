package com.sleeplessknights.donence.rest.request

import com.sleeplessknights.donence.model.Request
import com.sleeplessknights.donence.model.RequestResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class RequestRepository {

    private val requestApiService by lazy {
        RequestApiService.create()
    }

    suspend fun makeRequest(requestType: String): Call<RequestResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext requestApiService.request(
                Request(requestType)
            )
        }
    }
}