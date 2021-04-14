package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecyclePoint(
    @SerializedName("recyclePointDetail") val detail: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
) : Parcelable
