package com.example.services

import com.example.models.User

interface UserService {
    fun create(
        email: String,
        password: String,
        githubId: String? = null,
        githubAccessToken: String? = null,
        twitterId: String? = null
    ): User
    fun findByEmail(email: String): User?
    fun findById(id: Int): User?
    fun findByGithubId(githubId: String): User?
    fun findByTwitterId(twitterId: String): User?
    fun save(user: User)
}