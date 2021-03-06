package com.sleeplessknights.donence.base

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

inline fun <reified T> SharedPreferences.putAny(key: String, value: T) {
    val gson = GsonBuilder().create()
    val json = gson.toJson(value)
    this.edit().putString(key, json).apply()
}

inline fun <reified T> SharedPreferences.getAny(key: String): T? {
    val json = this.getString(key, "")
    return if (json.isNullOrEmpty()) {
        null
    } else {
        val gson = GsonBuilder().create()
        val type = object : TypeToken<T>() {}.type
        gson.fromJson<T>(json, type)
    }
}
