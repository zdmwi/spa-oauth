package com.example.controllers

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

interface AuthController {
    suspend fun register(call: ApplicationCall)
    suspend fun login(call: ApplicationCall)
    suspend fun refreshToken(call: ApplicationCall)
    suspend fun startGithubOAuth(call: ApplicationCall)
    suspend fun handleGithubOAuth(call: ApplicationCall)
}

fun Routing.auth(authController: AuthController) {
    route("auth") {
        post("register") { authController.register(this.context) }
        post("login") { authController.login(this.context) }
        post("refreshToken") { authController.refreshToken(this.context) }
        get("login/oauth/github/start") { authController.startGithubOAuth(this.context) }
        authenticate("github-oauth") {
            get("login/oauth/github/callback") { authController.handleGithubOAuth(this.context) }
        }
    }
}