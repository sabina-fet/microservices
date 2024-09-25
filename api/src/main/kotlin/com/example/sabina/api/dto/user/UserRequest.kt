package com.example.sabina.api.dto.user

/**
 * @author Sabina Muhic
 */
data class UserRequest (
    var username: String,
    val password: String,
    var firstName: String,
    var lastName: String,
    var address: String,
    var city: String,
    var country: String,
    var phoneNumber: String? = null,
    var enabled: Boolean? = false
)