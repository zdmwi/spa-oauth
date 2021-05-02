package com.example.models.payloads

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserRequest(val email: String, val password: String, val repeatedPassword: String)