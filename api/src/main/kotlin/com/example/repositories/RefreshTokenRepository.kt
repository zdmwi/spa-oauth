package com.example.repositories

import com.example.models.RefreshToken

interface RefreshTokenRepository {
    fun findById(id: Int): RefreshToken?
    fun findByToken(token: String): RefreshToken?
    fun save(refreshToken: RefreshToken): Int
}