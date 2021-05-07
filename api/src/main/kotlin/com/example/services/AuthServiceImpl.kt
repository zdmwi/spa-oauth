package com.example.services

import com.example.exceptions.ExistingUserException
import com.example.exceptions.InvalidCredentialsException
import com.example.exceptions.TokenRefreshException
import com.example.models.RefreshToken
import com.example.models.User
import com.example.models.payloads.LoginUserRequest
import com.example.models.payloads.RefreshTokenRequest
import com.example.models.payloads.RegisterUserRequest
import com.example.utils.arePasswordsEqual
import com.example.utils.convertDateToLocalDateTime
import com.example.utils.generateAccessToken
import com.example.utils.hashPassword
import java.time.LocalDateTime
import java.util.*

class AuthServiceImpl(
    private val userService: UserService,
    private val refreshTokenService: RefreshTokenService
) : AuthService {
    override fun registerUser(request: RegisterUserRequest) {
        val existingUser = userService.findByEmail(request.email)
        if (existingUser != null) throw ExistingUserException("Email address is already in use")

        val hashedPassword = hashPassword(request.password)
        val user = userService.create(request.email, hashedPassword)
        userService.save(user)
    }

    override fun loginUser(request: LoginUserRequest): User {
        fun User.hasDifferentPassword(plaintextPassword: String): Boolean =
            !arePasswordsEqual(plaintextPassword, password)

        val userFound = userService.findByEmail(request.email)
            ?: throw InvalidCredentialsException("Cannot find a user with this email address")

        if (userFound.hasDifferentPassword(request.password))
            throw InvalidCredentialsException("Incorrect password provided")

        return userFound
    }

    override fun refreshAccessToken(request: RefreshTokenRequest, secret: String): String? {
        fun RefreshToken.isExpired(): Boolean =
            expiryDate.isBefore(convertDateToLocalDateTime(Date()))

        fun RefreshToken.isNotAssociatedWithUser(): Boolean =
            user?.let { userService.findById(it) } == null

        val refreshToken = refreshTokenService.findByToken(request.refreshToken)
            ?: throw TokenRefreshException("Cannot find refresh token. Please log in again")

        if (refreshToken.isExpired())
            throw TokenRefreshException("Refresh token is expired. Please log in again")

        if (refreshToken.isNotAssociatedWithUser())
            throw TokenRefreshException("No user found for refresh token. Please log in again")

        val user = userService.findById(refreshToken.id!!)

        return user?.let { generateAccessToken(it, secret) }
    }
}