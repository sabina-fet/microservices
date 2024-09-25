package com.example.sabina.api.controllers

import com.example.sabina.api.dto.user.UserRequest
import com.example.sabina.api.mapper.UserMapper
import com.example.sabina.api.repositories.UserRepository
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    val userRepository: UserRepository,
    metricsRegistry: MeterRegistry,
    private val userMapper: UserMapper,
) {
    private val listUsersCounter = metricsRegistry.counter("list_users_total")

    @GetMapping
    fun listUsers() = ResponseEntity(userRepository.findAll(), HttpStatus.OK).also {
        listUsersCounter.increment()
    }

    @PostMapping
    fun createUser(@RequestBody newUser: UserRequest): ResponseEntity<Any> {
        when (val oldUser = userRepository.findByUsername(newUser.username)) {
            null -> userRepository.save(userMapper.mapUser(newUser)).also { println("creating new user") }
            else -> {
                userRepository.save(oldUser).also { println("Updating user") }
            }
        }
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/{user-id}")
    fun getUserById(@PathVariable("user-id") userId : Long) =
        ResponseEntity(userRepository.findById(userId), HttpStatus.OK)
}