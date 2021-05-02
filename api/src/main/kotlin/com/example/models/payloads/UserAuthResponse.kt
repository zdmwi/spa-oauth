package com.example.models.payloads

import com.example.models.User

data class UserAuthResponse(val accessToken: String, val refreshToken: String, val user: User)