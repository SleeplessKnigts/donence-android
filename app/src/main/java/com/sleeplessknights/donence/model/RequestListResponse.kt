package com.sleeplessknights.donence.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestListResponse(
    @SerializedName("requests") val requests: List<RequestResponse>
) : Parcelable