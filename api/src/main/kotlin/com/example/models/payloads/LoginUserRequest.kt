package com.example.models.payloads

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest(val email: String, val password: String)