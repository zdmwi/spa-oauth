package com.example.services

import com.example.exceptions.ExistingUserException
import com.example.exceptions.InvalidCredentialsException
import com.example.models.entities.User
import com.example.models.payloads.LoginUserRequest
import com.example.models.payloads.RegisterUserRequest
import com.example.repositories.UserRepository
import com.example.utils.arePasswordsEqual
import com.example.utils.hashPassword

class AuthServiceImpl(private val userRepository: UserRepository) : AuthService {
    override fun registerUser(request: RegisterUserRequest) {
        val existingUser = userRepository.findByEmail(request.email)
        if (existingUser != null) throw ExistingUserException("Email address is already in use")

        val hashedPassword = hashPassword(request.password)
        val user = User(email = request.email, password = hashedPassword)
        userRepository.create(user)
    }

    override fun loginUser(request: LoginUserRequest) {
        fun User.hasDifferentPassword(plaintextPassword: String) = !arePasswordsEqual(plaintextPassword, password)

        val userFound = userRepository.findByEmail(request.email)
            ?: throw InvalidCredentialsException("Cannot find a user with this email address")

        if (userFound.hasDifferentPassword(request.password)) throw InvalidCredentialsException("Incorrect password provided")
    }
}