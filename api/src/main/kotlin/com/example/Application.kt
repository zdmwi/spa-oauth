package com.example

import com.example.config.di
import com.example.controllers.AuthController
import com.example.controllers.auth
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.kodein.di.instance
import org.kodein.di.newInstance

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args);

fun Application.module(testing: Boolean = false) {
    install(CallLogging)
    install(ContentNegotiation) { json() }

    val authController by di.newInstance { AuthController(instance()) }
    install(Routing) {
        auth(authController)
    }
}