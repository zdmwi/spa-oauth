package com.example.controllers

import io.ktor.application.*
import io.ktor.routing.*

interface AuthController {
    suspend fun register(call: ApplicationCall)
    suspend fun login(call: ApplicationCall)
}

fun Routing.auth(authController: AuthController) {
    route("auth") {
        post("register") { authController.register(this.context) }
        post("login") { authController.login(this.context) }
    }
}