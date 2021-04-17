package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("email") val email: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("subAdminArea") val subAdminArea: String,
    @SerializedName("subLocality") val subLocality: String,
    @SerializedName("thoroughfare") val thoroughfare: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("deviceToken") val deviceToken: String,
    @SerializedName("fname") val fname: String
) : Parcelable