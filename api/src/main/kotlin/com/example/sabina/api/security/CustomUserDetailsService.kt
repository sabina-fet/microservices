package com.example.sabina.api.security

/**
 * @author Sabina Muhic
 */
import com.example.sabina.api.models.UserRole
import com.example.sabina.api.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApiUser = com.example.sabina.api.models.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username)?.let {
            mapToUserDetails(it)
        } ?: throw UsernameNotFoundException("Not found!")

    private fun mapToUserDetails(user: ApiUser): UserDetails =
        User.builder()
            .username(user.username)
            .password(user.password)
            .roles(user.role?.name ?: UserRole.USER.name)
            .build()
}