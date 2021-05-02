package com.example.models.payloads

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

@Serializable
data class RefreshTokenRequest(val refreshToken: String) {
    init {
        validate(this) {
            validate(RefreshTokenRequest::refreshToken).isNotBlank()
        }
    }
}
