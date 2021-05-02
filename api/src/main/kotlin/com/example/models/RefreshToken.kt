package com.example.models

import java.time.LocalDateTime

data class RefreshToken(
    val id: Int? = null,
    val user: Int? = null,
    val token: String,
    val expiryDate: LocalDateTime
)
