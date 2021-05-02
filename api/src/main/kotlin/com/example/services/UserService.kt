package com.example.services

import com.example.models.entities.User

interface UserService {
    fun create(email: String, password: String): User
    fun findByEmail(email: String): User?
    fun findById(id: Int): User?
    fun save(user: User): Int
}