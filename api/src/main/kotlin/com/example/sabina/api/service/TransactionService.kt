package com.example.sabina.api.service

import com.example.sabina.api.controllers.CreateTransactionRequest
import com.example.sabina.api.kafka.KafkaTransactionService
import com.example.sabina.api.models.Transaction
import com.example.sabina.api.models.TransactionStatus
import com.example.sabina.api.repositories.AccountRepository
import com.example.sabina.api.repositories.TransactionRepository
import com.example.sabina.api.utils.unwrap
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val kafkaTransactionService: KafkaTransactionService
) {

    @Transactional
    fun executeTransaction(request: CreateTransactionRequest): ResponseEntity<String> =
        accountRepository.findById(request.src).unwrap()?.let { account ->
            when (account.balance < request.amount) {
                true -> ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient funds")
                else -> {
                    account.balance -= request.amount;
                    accountRepository.save(account)
                    val transaction = Transaction(
                        id = null,
                        datetime = Instant.now().toEpochMilli(),
                        amount = - request.amount,
                        status = TransactionStatus.PENDING,
                        source = account,
                        destination = request.dest
                    )
                    transactionRepository.save(transaction)

                    val destinationAccount = accountRepository.findAccountByNumber(request.dest)
                    if (destinationAccount != null) {
                        destinationAccount.balance += request.amount
                        accountRepository.save(destinationAccount)
                    }

                    kafkaTransactionService.send(
                        KafkaTransactionMessage(
                            source = transaction.source!!.accountNumber!!,
                            destination = transaction.destination!!,
                            amount = transaction.amount!!,
                            datetime = transaction.datetime!!
                        )
                    )
                    ResponseEntity(HttpStatus.CREATED)
                }
            }
        } ?: ResponseEntity(HttpStatus.OK)
}

data class KafkaTransactionMessage(
    val source: String,
    val destination: String,
    val amount: Double,
    val datetime: Long
)