package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class RequestResponse(
    @SerializedName("requestId") val requestId: Number,
    @SerializedName("requestType") val requestType: String,
    @SerializedName("creationDate") val creationDate: LocalDateTime,
    @SerializedName("issuer") val issuer: LoginResponse,
    @SerializedName("active") val active: Boolean
) : Parcelable