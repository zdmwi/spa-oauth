package com.example.controllers

import com.example.models.User
import com.example.models.payloads.LoginUserRequest
import com.example.models.payloads.RefreshTokenRequest
import com.example.models.payloads.RegisterUserRequest
import com.example.models.payloads.TokenResponse
import com.example.services.AuthService
import com.example.services.RefreshTokenService
import com.example.services.UserService
import com.example.utils.generateAccessToken
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.serializer

class AuthControllerImpl(
    private val authService: AuthService,
    private val refreshTokenService: RefreshTokenService,
    private val userService: UserService,
) : AuthController {
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

                val refreshToken = refreshTokenService.create(this)
                refreshTokenService.save(refreshToken)

                val response = TokenResponse(accessToken, refreshToken.token)
                call.respond(response)
            }
        }
    }

    override suspend fun refreshToken(call: ApplicationCall) {
        val request = call.receive<RefreshTokenRequest>()
        authService.refreshAccessToken(request).apply {
            val response = TokenResponse(this!!, request.refreshToken)
            call.respond(response)
        }
    }

    override suspend fun startGithubOAuth(call: ApplicationCall) {
        call.respondRedirect("http://localhost:4288/auth/login/oauth/github/callback")
    }

    override suspend fun handleGithubOAuth(call: ApplicationCall) {
        val principal = call.authentication.principal<OAuthAccessTokenResponse.OAuth2>()

        val githubAccessToken = principal?.accessToken
        val response = HttpClient(CIO){
            defaultRequest { header("Authorization", "Token $githubAccessToken") }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }.use { client -> client.get<JsonObject>("https://api.github.com/user") }

        val githubId = response["id"].toString()
        val email = response["email"].toString()
        var user = userService.findByGithubId(githubId)

        // TODO: Clean this up. Saving and then searching to get the id for the inserted user is inefficient
        if (user == null) {
            user = userService.create(
                email = email,
                password = "",
                githubId = githubId,
                githubAccessToken = githubAccessToken)

            userService.save(user)
            user = user.githubId?.let { userService.findByGithubId(it) }
        }

        val jwtAccessToken = generateAccessToken(user!!)
        val refreshToken = refreshTokenService.create(user)

        call.respondRedirect("http://localhost:3000/login?accessToken=$jwtAccessToken&refreshToken=${refreshToken.token}")
    }
}