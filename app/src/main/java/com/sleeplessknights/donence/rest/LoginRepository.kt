package com.sleeplessknights.donence.rest

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.sleeplessknights.donence.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class LoginRepository() {

    //lazy object calling
    private val userApiService by lazy {
        UserApiService.create()
    }

    // BEWARE: This function uses coroutines *to connect to the backend userAuthentication endpoint*
    //         See https://developer.android.com/kotlin/coroutines for more information.
    //        -Dz
    suspend fun userAuth(account: GoogleSignInAccount?) {
        return withContext(Dispatchers.IO) {
            /* backend struggle starts here */
            var call: Call<User> = userApiService.authenticateUser(
                account?.email.toString(),
                account?.displayName.toString(),
                "google",
                "NA"
            ).also {
                val execute = it.execute()
            }
        }
    }
}

// DISCLAIMER (FOR NO REASON AT ALL):
// Function, class and package names, types, locations etc. are all subject to change.
// The goal is to provide basic functionality and understand how things work around here just for now.
// Happy coding, boys!
// -Dz