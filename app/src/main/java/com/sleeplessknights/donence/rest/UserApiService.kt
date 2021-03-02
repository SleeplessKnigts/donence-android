package com.sleeplessknights.donence.rest

import com.sleeplessknights.donence.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*
import retrofit2.converter.gson.GsonConverterFactory
import com.sleeplessknights.donence.rest.UserApiService
import retrofit2.http.Body

interface UserApiService {

    @POST("login")
    fun authenticateUser(@Body request: deneme): Call<User> // fun definition of authenticateUser ends here


    // As far as I understand, this object is the girlfriend of our bro interface UserApiService
    // We implement functions in here, they can be called as they are static functions
    // UserApiService.create() returns a UserApiService object *touched* by Retrofit API(???)
    // -Dz
    companion object {
        /* TODO: MAKE IT USE SSL!
        *        Normally, Android does not allow Cleartext communication.
        *        Since the backend server can not handle SSL handshakes yet, we have a workaround.
        *        CTRL+SHIFT+F usesClearText to see the workaround in AndroidManifest.xml
        *       -Dz */
        private val URL = "http://10.0.2.2:8080/api/auth/" /*TODO: fun fact: 10.0.2.2 refers to host loopback ip address in avd.
                                                  *       replace with real url _when the time comes_.
                                                  *      -Dz */
        fun create(): UserApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(UserApiService::class.java)
        }
    }

}

// DISCLAIMER (FOR NO REASON AT ALL):
// Function, class and package names, types, locations etc. are all subject to change.
// The goal is to provide basic functionality and understand how things work around here just for now.
// Happy struggling, boys!
// -Dz