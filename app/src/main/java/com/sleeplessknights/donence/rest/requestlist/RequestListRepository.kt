package com.sleeplessknights.donence.rest.requestlist

import com.sleeplessknights.donence.model.RequestListResponse
import com.sleeplessknights.donence.model.RequestResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class RequestListRepository {

    private val activeRequestListApiService by lazy {
        ActiveRequestListApiService.create()
    }

    private val oldRequestListApiService by lazy {
        OldRequestListApiService.create()
    }

    suspend fun makeRequestForActive(token: String): Call<List<RequestResponse>> {
        return withContext(Dispatchers.IO) {
            val header = ("Bearer ".plus(token))
            return@withContext activeRequestListApiService.request(header)
        }
    }

    suspend fun makeRequestForOld(token: String): Call<List<RequestResponse>> {
        return withContext(Dispatchers.IO) {
            val header = ("Bearer ".plus(token))
            return@withContext oldRequestListApiService.request(header)
        }
    }
}