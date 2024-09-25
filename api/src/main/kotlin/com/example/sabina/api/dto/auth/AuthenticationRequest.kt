package com.example.sabina.api.dto.auth

data class AuthenticationRequest(
    val username: String,
    val password: String,
)