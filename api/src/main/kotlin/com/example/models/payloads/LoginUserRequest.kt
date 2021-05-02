package com.example.models.payloads

import kotlinx.serialization.Serializable
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

@Serializable
data class LoginUserRequest(val email: String, val password: String) {
    init {
        validate(this) {
            validate(LoginUserRequest::email).isNotBlank().isEmail()
            validate(LoginUserRequest::password).isNotBlank()
        }
    }
}