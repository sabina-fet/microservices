package com.example.sabina.api.controllers

import com.example.sabina.api.repositories.AccountRepository
import com.example.sabina.api.repositories.TransactionRepository
import com.example.sabina.api.service.TransactionService
import com.example.sabina.api.utils.unwrap
import io.micrometer.core.instrument.MeterRegistry
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/api/transactions")
class TransactionController(
    private val transactionService: TransactionService,
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
    metricsRegistry: MeterRegistry
) {
    companion object {
        private val log = logger {}
    }

    private val listTransactionsCounter = metricsRegistry.counter("list_transactions_total")

    @GetMapping("/{account-id}")
    fun listTransactions(@PathVariable("account-id") accountId: Long) =
        accountRepository.findById(accountId).unwrap()?.let {
            listTransactionsCounter.increment()
            ResponseEntity(transactionRepository.findBySource(it), HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PostMapping
    fun createTransaction(@RequestBody request: CreateTransactionRequest): ResponseEntity<String> {
        log.debug { "Creating new transaction: $request" }
        return transactionService.executeTransaction(request)
    }

    @GetMapping("/{transaction-id}/transaction")
    fun getTransactionById(@PathVariable("transaction-id") transactionId: Long) =
        ResponseEntity(transactionRepository.findById(transactionId), HttpStatus.OK)
}

data class CreateTransactionRequest(
    val src: Long,
    val dest: String,
    val amount: Double
)