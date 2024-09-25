package com.example.sabina.api.dto.auth

/**
 * @author Sabina Muhic
 */
data class AuthenticationResponse(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String,
    val name: String
)