package com.example.services

import com.example.models.User
import com.example.repositories.UserRepository

class UserServiceImpl(private val userRepository: UserRepository): UserService {
    override fun create(email: String, password: String, githubId: String?, githubAccessToken: String?): User {
        return User(
            email = email,
            password = password,
            githubId = githubId,
            githubAccessToken = githubAccessToken
        )
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun findById(id: Int): User? {
        return userRepository.findById(id)
    }

    override fun findByGithubId(githubId: String): User? {
        return userRepository.findByGithubId(githubId)
    }

    override fun save(user: User) {
        userRepository.save(user)
    }
}