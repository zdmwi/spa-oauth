package com.example.controllers

import com.example.models.payloads.LoginUserRequest
import com.example.models.payloads.RegisterUserRequest
import com.example.models.payloads.TokenResponse
import com.example.services.AuthService
import com.example.utils.generateAccessToken
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*

class AuthControllerImpl(private val authService: AuthService): AuthController {
    override suspend fun register(call: ApplicationCall) {
        call.receive<RegisterUserRequest>().apply {
            authService.registerUser(this)
            call.respond(this)
        }
    }

    override suspend fun login(call: ApplicationCall) {
        call.receive<LoginUserRequest>().apply {
            authService.loginUser(this).apply {
                val accessToken = generateAccessToken(this)
                val response = TokenResponse(accessToken)
                call.respond(response)
            }
        }
    }
}