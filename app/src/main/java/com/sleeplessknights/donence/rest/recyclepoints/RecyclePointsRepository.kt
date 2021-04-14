package com.sleeplessknights.donence.rest.recyclepoints

import com.sleeplessknights.donence.model.RecyclePoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class RecyclePointsRepository() {
    private val recyclePointsApiService by lazy {
        RecyclePointsApiService.create()
    }

    suspend fun getRecyclePoints(token: String): Call<List<RecyclePoint>> {
        return withContext(Dispatchers.IO) {
            val header = ("Bearer ".plus(token))
            return@withContext recyclePointsApiService.getRecyclePoints(header)
        }
    }
}