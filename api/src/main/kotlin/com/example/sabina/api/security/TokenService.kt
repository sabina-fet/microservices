package com.example.sabina.api.security

import com.example.sabina.api.configuration.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import mu.KLogging
import mu.KotlinLogging.logger
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author Sabina Muhic
 */
@Service
class TokenService(jwtProperties: JwtProperties) {

    companion object {
        private val log = logger {}
    }

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    fun isValid(token: String, userDetails: UserDetails) =
        when {
            isExpired(token) -> false
            else -> extractUsername(token) == userDetails.username
        }

    fun extractUsername(token: String): String? =
        getAllClaims(token)
            ?.subject

    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            ?.expiration
            ?.before(Date(System.currentTimeMillis()))
            ?: true

    private fun getAllClaims(token: String): Claims? =
        try {
            val parser = Jwts.parser()
                .verifyWith(secretKey)
                .build()

            parser.parseSignedClaims(token).payload

        } catch (e: ExpiredJwtException) {
            log.info { "Token expired: $token" }
            null
        }
}