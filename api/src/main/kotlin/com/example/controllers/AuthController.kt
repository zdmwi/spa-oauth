package com.example.controllers

import io.ktor.application.*

interface AuthController {
    suspend fun login(call: ApplicationCall)
    suspend fun register(call: ApplicationCall)
}