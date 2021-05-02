package com.example.services

import com.example.models.RefreshToken
import com.example.repositories.RefreshTokenRepository

class RefreshTokenServiceImpl(private val refreshTokenRepository: RefreshTokenRepository): RefreshTokenService {
    override fun create(): RefreshToken {
        val tokenLifespanInMs = 3_600_000L * 24 * 30 // 30 days
        TODO("Not yet implemented")
    }

    override fun findByToken(token: String): RefreshToken? {
        return refreshTokenRepository.findByToken(token)
    }

    override fun save(refreshToken: RefreshToken) {
        refreshTokenRepository.save(refreshToken)
    }
}