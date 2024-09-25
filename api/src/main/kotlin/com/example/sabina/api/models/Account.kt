package com.example.sabina.api.models

import com.fasterxml.jackson.annotation.*
import jakarta.persistence.*

@Entity(name = "account")
data class Account (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "serial")
    var id: Long? = null,

    @Column(name = "account_number", nullable = false)
    var accountNumber: String? = null,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    var type: AccountType = AccountType.CURRENT,

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    var owner: User? = null,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: AccountStatus = AccountStatus.ACTIVE,

    @Column(name = "balance", nullable = false)
    var balance: Double = 0.0,

    @Column(name = "currency", nullable = false)
    var currency: Currency = Currency.EUR
)