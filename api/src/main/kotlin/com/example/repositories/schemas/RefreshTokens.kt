package com.example.repositories.schemas

import com.example.models.RefreshToken
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime

internal object RefreshTokens : Table() {
    val id = integer("id").autoIncrement()
    val token = varchar("token", 128)
    val userId = (integer("user_id") references Users.id).nullable()
    val expiryDate = datetime("expiry_date")

    override val primaryKey = PrimaryKey(id, name = "PK_RefreshToken_ID")

    fun toDomain(row: ResultRow): RefreshToken {
        return RefreshToken(
            id = row[id],
            token = row[token],
            user = row[userId],
            expiryDate = row[expiryDate]
        )
    }
}