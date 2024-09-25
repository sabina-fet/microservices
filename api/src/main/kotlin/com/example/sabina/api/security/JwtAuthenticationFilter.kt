package com.example.sabina.api.security

import com.example.sabina.api.models.toUserDetails
import com.example.sabina.api.repositories.UserRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userRepository: UserRepository,
    private val tokenService: TokenService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        if (authHeader.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken = authHeader!!.extractTokenValue()
        if (tokenService.isExpired(jwtToken)){
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return
        }

        val username = tokenService.extractUsername(jwtToken)

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            userRepository.findByUsername(username)?.let {
                val userDetails = it.toUserDetails()
                if (tokenService.isValid(jwtToken, userDetails))
                    updateContext(userDetails, request)

                filterChain.doFilter(request, response)
            }
        }
    }

    private fun String?.doesNotContainBearerToken() =
        this == null || !this.startsWith("Bearer ")

    private fun String.extractTokenValue() =
        this.substringAfter("Bearer ")

    private fun updateContext(foundUser: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(foundUser, null, foundUser.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }
}