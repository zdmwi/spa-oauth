package com.example.services

import com.example.models.RefreshToken

interface RefreshTokenService {
    fun create(): RefreshToken
    fun findByToken(token: String): RefreshToken?
    fun save(refreshToken: RefreshToken)
}