package com.example.sabina.api.utils

import java.util.*

fun <T> Optional<T>.unwrap(): T? = orElse(null)
