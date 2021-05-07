package com.example.models

import io.ktor.auth.*

data class User(
    val id: Int? = null,
    val githubId: String? = null,
    val githubAccessToken: String? = null,
    val twitterId: String? = null,
    val email: String,
    val password: String,
): Principal