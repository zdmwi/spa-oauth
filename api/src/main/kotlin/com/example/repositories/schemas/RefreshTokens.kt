package com.example.repositories.schemas

import com.example.models.RefreshToken
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

internal object RefreshTokens : IntIdTable() {
    val token = varchar("token", 128)

    fun toDomain(row: ResultRow): RefreshToken {
        return RefreshToken(
            id = row[id].value,
            token = row[token]
        )
    }
}