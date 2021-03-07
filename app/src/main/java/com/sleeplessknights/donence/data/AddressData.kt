package com.sleeplessknights.donence.data

import com.google.gson.annotations.SerializedName

data class AddressData(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)

