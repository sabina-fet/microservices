package com.example.sabina.api.mapper

import com.example.sabina.api.dto.user.UserRequest
import com.example.sabina.api.models.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper {

    private val passEncoder = BCryptPasswordEncoder()

    fun mapUser(request: UserRequest): User {
        return User(
            username = request.username,
            password = passEncoder.encode(request.password),
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.username,
            address = request.address,
            phoneNumber = request.phoneNumber,
            city = request.city,
            country = request.country,
            enabled = request.enabled,
        )
    }
}
