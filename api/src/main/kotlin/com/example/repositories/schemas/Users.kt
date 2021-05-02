package com.example.repositories.schemas

import com.example.models.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

internal object Users : IntIdTable() {
    val email = varchar("email", 128)
    val password = varchar("password", 128)

    fun toDomain(row: ResultRow): User {
        return User(
            id = row[id].value,
            email = row[email],
            password = row[password],
        )
    }
}