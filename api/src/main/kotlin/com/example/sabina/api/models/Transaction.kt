package com.example.sabina.api.models

import com.fasterxml.jackson.annotation.*
import jakarta.persistence.*
import java.time.Instant

@Entity(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(columnDefinition = "serial")
    var id: Long? = null,

    @Column(name = "datetime", nullable = false)
    var datetime: Long? = Instant.now().toEpochMilli(),

    @Column(name = "amount", nullable = false)
    var amount: Double? = null,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: TransactionStatus? = TransactionStatus.PENDING,

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    var source: Account? = null,

    @Column(name = "destination", nullable = false)
    var destination: String? = null,
)