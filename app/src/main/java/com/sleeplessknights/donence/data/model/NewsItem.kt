package com.sleeplessknights.donence.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class NewsItem(
    @SerializedName("heading") val email: String,
    @SerializedName("content") val fname: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("createdAt") val lng: LocalDateTime  // şuna dikkat
)
