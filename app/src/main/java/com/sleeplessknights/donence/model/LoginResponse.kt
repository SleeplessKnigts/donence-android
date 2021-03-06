package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("role") val role: String,
    @SerializedName("name") val name: String
) : Parcelable
