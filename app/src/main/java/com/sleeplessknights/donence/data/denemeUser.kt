package com.sleeplessknights.donence.data

import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName

data class denemeUser(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("authProvider") val authProvider: String,
    @SerializedName("imageUrl") val imageUrl: String
)