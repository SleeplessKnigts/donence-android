package com.sleeplessknights.donence.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("authProvider") val authProvider: String,
    @SerializedName("imageUrl") val imageUrl: String
)
