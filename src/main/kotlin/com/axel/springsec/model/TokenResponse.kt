package com.axel.springsec.model

data class TokenResponse(
    val accessToken:String,
    val refreshToken:String,
)
