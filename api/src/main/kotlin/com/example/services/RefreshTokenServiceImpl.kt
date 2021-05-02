package com.example.services

import com.example.models.RefreshToken
import com.example.repositories.RefreshTokenRepository
import com.example.utils.convertDateToLocalDateTime
import java.util.*

class RefreshTokenServiceImpl(private val refreshTokenRepository: RefreshTokenRepository) : RefreshTokenService {
    override fun create(): RefreshToken {
        val tokenLifespanInMs = 3_600_000L * 24 * 30 // 30 days
        val expiryDate = Date(System.currentTimeMillis() + tokenLifespanInMs)
        return RefreshToken(
            token = UUID.randomUUID().toString(),
            expiryDate = convertDateToLocalDateTime(expiryDate)
        )
    }

    override fun findByToken(token: String): RefreshToken? {
        return refreshTokenRepository.findByToken(token)
    }

    override fun save(refreshToken: RefreshToken) {
        refreshTokenRepository.save(refreshToken)
    }
}