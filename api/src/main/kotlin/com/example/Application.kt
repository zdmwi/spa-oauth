package com.example

import com.example.controllers.AuthControllerImpl
import com.example.controllers.auth
import com.example.services.AuthServiceImpl
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args);

fun Application.module(testing: Boolean = false) {
    install(CallLogging)
    install(ContentNegotiation) { json() }
    install(Routing) {
        // TODO: Setup dependency injection using codein
        auth(AuthControllerImpl(AuthServiceImpl()))
    }
}