package com.psybot.model.security


data class AuthRequest(
    val username: String,
    val password: String
)

data class AuthResponse(val token: String)