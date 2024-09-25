package com.example.sabina.api.repositories

import com.example.sabina.api.models.Account
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Long> {
    fun findAccountsByOwnerId(ownerId: Long): List<Account>?

    @Query(value = "select * from account where account_number = :accountNumber", nativeQuery = true)
    fun findAccountByNumber(accountNumber: String): Account?
}