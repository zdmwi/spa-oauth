package com.example.repositories

import com.example.models.User

interface UserRepository {
    fun findById(id: Int): User?
    fun findByEmail(email: String): User?
    fun save(user: User)
}