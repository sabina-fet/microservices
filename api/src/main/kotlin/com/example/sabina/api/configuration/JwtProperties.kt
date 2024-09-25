package com.example.sabina.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author Sabina Muhic
 */
@ConfigurationProperties("jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
)