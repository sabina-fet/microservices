package com.example.sabina.api.repositories

import com.example.sabina.api.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByFirstName(name: String): User?
    fun findByUsername(email: String): User?
}