package com.example.sabina.api.repositories

import com.example.sabina.api.models.Session
import org.springframework.data.repository.CrudRepository


interface SessionRepository: CrudRepository<Session, Long> {
    fun findByUserId(userId: Long): Session?
    fun findByAccessToken(accessToken: String): Session?
    fun findByRefreshToken(refreshToken: String): Session?
}