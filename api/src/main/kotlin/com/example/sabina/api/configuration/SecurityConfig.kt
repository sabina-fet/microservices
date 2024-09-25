package com.example.sabina.api.configuration

import com.example.sabina.api.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): DefaultSecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors().configurationSource {
                CorsConfiguration().also {
                    it.allowedOrigins = listOf("*")
                    it.allowedHeaders = listOf("*")
                    it.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
                }
            }.and()
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/auth", "api/auth/refresh", "/error", "/actuator/prometheus", "/health")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/user")
                    .permitAll()
                    .requestMatchers("/api/users**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}