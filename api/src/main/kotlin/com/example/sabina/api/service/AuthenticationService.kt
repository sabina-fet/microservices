package com.example.sabina.api.service

/**
 * @author Sabina Muhic
 */
import com.example.sabina.api.configuration.JwtProperties
import com.example.sabina.api.dto.auth.AuthenticationRequest
import com.example.sabina.api.dto.auth.AuthenticationResponse
import com.example.sabina.api.models.Session
import com.example.sabina.api.models.toUserDetails
import com.example.sabina.api.repositories.SessionRepository
import com.example.sabina.api.repositories.UserRepository
import com.example.sabina.api.security.CustomUserDetailsService
import com.example.sabina.api.security.TokenService
import com.example.sabina.api.utils.unwrap
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
) {
    fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse? {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )
        return userRepository.findByUsername(authenticationRequest.username)?.let {
            val userDetails = it.toUserDetails()
            val accessToken = createAccessToken(userDetails)
            val refreshToken = createRefreshToken(userDetails)
            sessionRepository.save(Session(
                id = null,
                accessToken = accessToken,
                refreshToken = refreshToken,
                userId = it.id
            ))

            AuthenticationResponse(
                accessToken = accessToken,
                refreshToken = refreshToken,
                userId = it.id!!,
                name = it.firstName + " " + it.lastName
            )
        }
    }

    // This has to be reworked
    fun refreshAccessToken(refreshToken: String): String? {
        if (tokenService.isExpired(refreshToken)) return null

        return tokenService.extractUsername(refreshToken)?.let { username ->
            userRepository.findByUsername(username)?.let { user ->
                val userDetails = user.toUserDetails()
                sessionRepository.findByRefreshToken(refreshToken)?.let { session ->
                    if (user.username == username) {
                       val accessToken = createAccessToken(userDetails)
                        session.accessToken = accessToken
                        sessionRepository.save(session)
                        accessToken
                    } else {
                        null
                    }
                }
            }
        }
    }

    private fun createAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = getAccessTokenExpiration()
    )
    private fun createRefreshToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = getRefreshTokenExpiration()
    )
    private fun getAccessTokenExpiration(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    private fun getRefreshTokenExpiration(): Date =
        Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
}