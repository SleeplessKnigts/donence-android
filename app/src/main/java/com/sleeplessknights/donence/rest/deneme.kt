package com.sleeplessknights.donence.rest

import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName

data class deneme(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("authProvider") val authProvider: String,
    @SerializedName("imageUrl") val imageUrl: String
)
