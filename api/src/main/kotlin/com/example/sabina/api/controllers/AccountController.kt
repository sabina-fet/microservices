package com.example.sabina.api.controllers;

import com.example.sabina.api.models.Account
import com.example.sabina.api.models.AccountStatus
import com.example.sabina.api.models.AccountType
import com.example.sabina.api.models.Currency
import com.example.sabina.api.repositories.AccountRepository
import com.example.sabina.api.repositories.UserRepository
import com.example.sabina.api.utils.unwrap
import io.micrometer.core.instrument.MeterRegistry
import mu.KotlinLogging.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * @author Sabina Muhic
 */
@RestController
@RequestMapping("/api/accounts")
class AccountController(
    private val accountRepository:AccountRepository,
    private val userRepository: UserRepository,
    metricsRegistry:MeterRegistry
) {
    companion object {
        private val log = logger {}
    }
    private val listAccountsCounter = metricsRegistry.counter("list_accounts_total")

    @GetMapping("/{owner-id}")
    fun listAccounts(@PathVariable("owner-id") ownerId: Long) =
        accountRepository.findAccountsByOwnerId(ownerId)?.let {
            listAccountsCounter.increment()
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PostMapping
    fun createAccount(@RequestBody req: CreateAccountRequest): ResponseEntity<HttpStatus> =
        userRepository.findById(req.ownerId).unwrap()?.let {
            accountRepository.save(Account(
                id = null,
                accountNumber = UUID.randomUUID().toString(),
                type = AccountType.CURRENT,
                owner = it,
                status = AccountStatus.ACTIVE,
                balance = 1000.0,
                currency = req.currency
            ))
            log.info("Creating new account $req")
            ResponseEntity(HttpStatus.CREATED)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
}

data class CreateAccountRequest (
    var ownerId: Long,
    var type: AccountType = AccountType.CURRENT,
    var currency: Currency = Currency.EUR
)
