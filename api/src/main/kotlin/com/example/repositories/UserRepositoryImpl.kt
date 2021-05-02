package com.example.repositories

import com.example.models.entities.User
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

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

class UserRepositoryImpl : UserRepository {
    init {
        transaction {
            SchemaUtils.create(Users)
        }
    }

    override fun findById(id: Int): User? {
        return transaction {
            Users
                .select { Users.id eq id }
                .map { Users.toDomain(it) }
                .firstOrNull()
        }
    }

    override fun findByEmail(email: String): User? {
        return transaction {
            Users
                .select { Users.email eq email }
                .map { Users.toDomain(it) }
                .firstOrNull()
        }
    }

    override fun create(user: User): Int {
        return transaction {
            Users.insertAndGetId {
                it[email] = user.email
                it[password] = user.password
            }.value
        }
    }
}