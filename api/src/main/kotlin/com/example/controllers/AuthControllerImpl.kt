package com.example.controllers

import com.example.models.payloads.LoginUserRequest
import com.example.models.payloads.RegisterUserRequest
import com.example.services.AuthService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

class AuthControllerImpl(private val authService: AuthService): AuthController {
    override suspend fun register(call: ApplicationCall) {
        call.receive<RegisterUserRequest>().apply {
            authService.registerUser(this)
            call.respond(this)
        }
    }

    override suspend fun login(call: ApplicationCall) {
        call.receive<LoginUserRequest>().apply {
            authService.loginUser(this)
            call.respond(this)
        }
    }
}