package com.example.models.payloads

import kotlinx.serialization.Serializable
import org.valiktor.functions.isEmail
import org.valiktor.functions.isEqualTo
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

@Serializable
data class RegisterUserRequest(val email: String, val password: String, val repeatedPassword: String) {
    init {
        validate(this) {
            validate(RegisterUserRequest::email).isNotBlank().isEmail()
            validate(RegisterUserRequest::password).isNotBlank()
            validate(RegisterUserRequest::repeatedPassword).isNotBlank().isEqualTo(password)
        }
    }
}