package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.User
import java.util.*

private const val timeValidForInMs = 3_600_000 * 10 // 10 hours
private const val issuer = "https://example.com"
const val audience = "spa-oauth-audience"
private const val subject = "authentication"
private val algorithm = Algorithm.HMAC256("super-secret")

fun generateAccessToken(user: User): String =
    JWT
        .create()
        .withIssuedAt(Date())
        .withSubject(subject)
        .withIssuer(issuer)
        .withAudience(audience)
        .withClaim("id", user.id!!)
        .withExpiresAt(Date(System.currentTimeMillis() + timeValidForInMs))
        .sign(algorithm)