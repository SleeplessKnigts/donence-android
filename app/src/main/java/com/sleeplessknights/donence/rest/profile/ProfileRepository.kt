package com.sleeplessknights.donence.rest.address

import com.sleeplessknights.donence.data.model.ProfileItem
import com.sleeplessknights.donence.rest.profile.ProfileApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class ProfileRepository() {

    private val profileApiService by lazy {
        ProfileApiService.create()
    }

    suspend fun getDetails(headerToken: String) : Call<ProfileItem> {

        return withContext(Dispatchers.IO) {
            /* backend struggle starts here */
            val son = ("Bearer ".plus(headerToken))
            return@withContext profileApiService.getProfile(son)
        }
    }
}