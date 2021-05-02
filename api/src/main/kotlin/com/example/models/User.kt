package com.example.models

import io.ktor.auth.*

data class User(
    val id: Int? = null,
    val email: String,
    val password: String,
): Principal