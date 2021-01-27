package com.sleeplessknights.donence.model

data class User(
    val email:          String,
    val name:           String,
    val authProvider:   String,
    val imageURL:       String
    )
