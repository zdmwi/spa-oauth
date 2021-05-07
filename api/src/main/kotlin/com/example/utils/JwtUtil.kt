package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.User
import java.util.*

private const val timeValidForInMs = 60_000 * 1 // 1 hour
private const val issuer = "https://example.com"
const val audience = "spa-oauth-audience"
private const val subject = "authentication"

fun generateAccessToken(user: User, secret: String): String =
    JWT
        .create()
        .withIssuedAt(Date())
        .withSubject(subject)
        .withIssuer(issuer)
        .withAudience(audience)
        .withClaim("id", user.id!!)
        .withExpiresAt(Date(System.currentTimeMillis() + timeValidForInMs))
        .sign(Algorithm.HMAC256(secret))