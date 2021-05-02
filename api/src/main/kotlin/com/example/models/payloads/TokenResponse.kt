package com.example.models.payloads

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(val accessToken: String, val refreshToken: String)