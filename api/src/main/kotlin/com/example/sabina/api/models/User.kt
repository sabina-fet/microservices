package com.example.sabina.api.models

import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails

typealias SecurityUser = org.springframework.security.core.userdetails.User

@Entity(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "serial")
    var id: Long? = null,

    @Column(name = "username")
    var username: String? = null,

    @Column(name = "password")
    var password: String? = null,

    @Column(name = "salt")
    var salt: String? = null,

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @Column(name = "address")
    var address: String? = null,

    @Column(name = "city")
    var city: String? = null,

    @Column(name = "country")
    var country: String? = null,

    @Column(name = "phone_number")
    var phoneNumber: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "enabled")
    var enabled: Boolean? = false,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: UserRole? = UserRole.USER
)

fun User.toUserDetails(): UserDetails =
    SecurityUser.builder()
        .username(this.username)
        .password(this.password)
        .roles(this.role?.name ?: UserRole.USER.name)
        .build()
