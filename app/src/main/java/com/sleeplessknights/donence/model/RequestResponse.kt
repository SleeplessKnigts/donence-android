package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class RequestResponse(
    @SerializedName("requestId") val requestId: Int,
    @SerializedName("requestType") val requestType: String,
    @SerializedName("creationDate") val creationDate: String,
    @SerializedName("issuer") val issuer: UserResponse,
    @SerializedName("active") val active: Boolean
) : Parcelable