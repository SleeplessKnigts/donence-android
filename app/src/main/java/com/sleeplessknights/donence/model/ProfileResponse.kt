package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("fname") val email: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
) : Parcelable
