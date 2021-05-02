package com.example.repositories

import com.example.models.entities.User

interface UserRepository {
    fun findById(id: Int): User?
    fun findByEmail(email: String): User?
    fun create(user: User): Int
}