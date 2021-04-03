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

    suspend fun makeRequest(token: String, requestType: String): Call<RequestResponse> {
        return withContext(Dispatchers.IO) {
            val req = ("Bearer ".plus(token))
            return@withContext requestApiService.request(req,
                Request(requestType)
            )
        }
    }
}