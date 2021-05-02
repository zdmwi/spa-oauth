package com.example.services

import com.example.models.RefreshToken
import com.example.models.User

interface RefreshTokenService {
    fun create(user: User): RefreshToken
    fun findByToken(token: String): RefreshToken?
    fun save(refreshToken: RefreshToken)
}