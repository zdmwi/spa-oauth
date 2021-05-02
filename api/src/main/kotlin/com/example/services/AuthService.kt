package com.example.services

import com.example.models.payloads.LoginUserRequest
import com.example.models.payloads.RegisterUserRequest

interface AuthService {
    fun registerUser(request: RegisterUserRequest)
    fun loginUser(request: LoginUserRequest)
}