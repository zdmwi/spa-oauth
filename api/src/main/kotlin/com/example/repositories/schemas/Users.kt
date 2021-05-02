package com.example.repositories.schemas

import com.example.models.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

internal object Users : Table() {
    val id = integer("id").autoIncrement()
    val email = varchar("email", 128)
    val password = varchar("password", 128)

    override val primaryKey = PrimaryKey(id, name = "PK_Users_ID")

    fun toDomain(row: ResultRow): User {
        return User(
            id = row[id],
            email = row[email],
            password = row[password],
        )
    }
}