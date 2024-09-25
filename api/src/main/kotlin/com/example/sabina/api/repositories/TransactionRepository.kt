package com.example.sabina.api.repositories

import com.example.sabina.api.controllers.CreateTransactionRequest
import com.example.sabina.api.models.Account
import com.example.sabina.api.models.Transaction
import com.example.sabina.api.models.TransactionStatus
import com.example.sabina.api.utils.unwrap
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.Instant

interface TransactionRepository : CrudRepository<Transaction, Long> {
    fun findBySource(source: Account): List<Transaction>?
}