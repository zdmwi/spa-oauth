package com.example.repositories.schemas

import com.example.models.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

internal object Users : Table() {
    val id = integer("id").autoIncrement()
    val githubId = varchar("github_id", 128).nullable()
    val githubAccessToken = varchar("github_access_token", 128).nullable()
    val twitterId = varchar("twitter_id", 128).nullable()
    val email = varchar("email", 128)
    val password = varchar("password", 128)

    override val primaryKey = PrimaryKey(id, name = "PK_Users_ID")

    fun toDomain(row: ResultRow): User {
        return User(
            id = row[id],
            githubId = row[githubId],
            githubAccessToken = row[githubAccessToken],
            twitterId = row[twitterId],
            email = row[email],
            password = row[password],
        )
    }
}