package com.sleeplessknights.donence.model

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("request_type") val requestType: String
)
