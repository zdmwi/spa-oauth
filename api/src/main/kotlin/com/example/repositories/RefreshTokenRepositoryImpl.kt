package com.example.repositories

import com.example.models.RefreshToken
import com.example.repositories.schemas.RefreshTokens
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


class RefreshTokenRepositoryImpl: RefreshTokenRepository {
    init {
        transaction {
            SchemaUtils.create(RefreshTokens)
        }
    }

    override fun findById(id: Int): RefreshToken? = transaction {
        RefreshTokens
            .select { RefreshTokens.id eq id }
            .map { RefreshTokens.toDomain(it) }
            .firstOrNull()
    }

    override fun findByToken(token: String): RefreshToken? = transaction {
        RefreshTokens
            .select {RefreshTokens.token eq token}
            .map { RefreshTokens.toDomain(it) }
            .firstOrNull()
    }

    override fun save(refreshToken: RefreshToken): Int = transaction {
        RefreshTokens
            .insertAndGetId {
                it[token] = refreshToken.toString()
            }.value
    }
}