package com.sleeplessknights.donence.model

data class LoginResponse(
    val accessToken: String,
    val email: String,
    val username: String,
    val role: String,
    val name: String
)
