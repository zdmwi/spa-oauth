package com.example.models

import java.time.LocalDateTime

data class RefreshToken(
    val id: Int?,
    val user: Int?,
    val token: String,
    val expiryDate: LocalDateTime
)
