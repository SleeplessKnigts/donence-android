package com.sleeplessknights.donence.data

import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName

data class deneme(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)