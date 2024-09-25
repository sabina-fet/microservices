package com.example.sabina.api.controllers

import com.example.sabina.api.dto.auth.AuthenticationRequest
import com.example.sabina.api.dto.auth.AuthenticationResponse
import com.example.sabina.api.dto.auth.RefreshTokenRequest
import com.example.sabina.api.dto.auth.TokenResponse
import com.example.sabina.api.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): ResponseEntity<AuthenticationResponse> =
        authenticationService.authentication(authRequest)?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest
    ): ResponseEntity<TokenResponse> =
        authenticationService.refreshAccessToken(request.token)?.let {
            ResponseEntity(TokenResponse(it), HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
}