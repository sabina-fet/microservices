package com.example.sabina.api.models

enum class UserRole {
    USER, ADMIN
}

enum class AccountStatus {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    CLOSED
}

enum class AccountType {
    CURRENT,
    SAVINGS,
    LOAN
}

enum class TransactionStatus {
    PENDING,
    COMPLETED,
    REJECTED,
    SIGNED,
    WITHDRAWN
}

enum class Currency {
    EUR,
    USD,
    BAM,
    JPY,
    GBP,
    AUD,
    CAD,
    CHF,
    CNH,
    HKD,
    NZD
}
