package com.sleeplessknights.donence.model

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("requestType") val requestType: String
)
