package com.example.sabina.api.models

import jakarta.persistence.*

@Entity(name = "session")
data class Session (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "serial")
    var id: Long? = null,

    @Column(name = "session_token", nullable = false)
    var accessToken: String? = null,

    @Column(name = "refresh_token", nullable = false)
    var refreshToken: String? = null,

    @Column(name = "user_id", nullable = true)
    var userId: Long? = null,
)