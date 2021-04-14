package com.sleeplessknights.donence.data.model

import com.google.gson.annotations.SerializedName

data class ProfileItem(
    @SerializedName("email") val email: String,
    @SerializedName("fname") val fname: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)
