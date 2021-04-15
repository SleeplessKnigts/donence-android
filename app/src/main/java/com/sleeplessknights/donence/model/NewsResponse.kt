package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(
    @SerializedName("heading") val accessToken: String,
    @SerializedName("content") val email: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("createdAt") val lat: String
) : Parcelable
