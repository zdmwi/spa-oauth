package com.example.repositories

import com.example.models.User
import com.example.repositories.schemas.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImpl : UserRepository {
    init {
        transaction {
            SchemaUtils.create(Users)
        }
    }

    override fun findById(id: Int): User? = transaction {
        Users
            .select { Users.id eq id }
            .map { Users.toDomain(it) }
            .firstOrNull()
    }

    override fun findByEmail(email: String): User? = transaction {
        Users
            .select { Users.email eq email }
            .map { Users.toDomain(it) }
            .firstOrNull()
    }

    override fun save(user: User): Int = transaction {
        Users.insertAndGetId {
            it[email] = user.email
            it[password] = user.password
        }.value
    }
}