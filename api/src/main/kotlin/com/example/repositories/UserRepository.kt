package com.example.repositories

import com.example.models.User

interface UserRepository {
    fun findById(id: Int): User?
    fun findByGithubId(githubId: String): User?
    fun findByTwitterId(twitterId: String): User?
    fun findByEmail(email: String): User?
    fun save(user: User)
}