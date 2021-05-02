package com.example.services

import com.example.models.User
import com.example.repositories.UserRepository

class UserServiceImpl(private val userRepository: UserRepository): UserService {
    override fun create(email: String, password: String): User {
        return User(email = email, password = password)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun findById(id: Int): User? {
        return userRepository.findById(id)
    }

    override fun save(user: User): Int {
        return userRepository.save(user)
    }
}