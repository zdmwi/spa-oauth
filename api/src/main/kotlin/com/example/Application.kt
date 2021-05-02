package com.example

import com.example.config.di
import com.example.controllers.AuthControllerImpl
import com.example.controllers.auth
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.instance
import org.kodein.di.newInstance

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun connectToDatabase() {
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")

    transaction {
        addLogger(StdOutSqlLogger)
    }
}

fun Application.module(testing: Boolean = false) {
    connectToDatabase()

    install(CallLogging)
    install(ContentNegotiation) { json() }

    val authController by di.newInstance { AuthControllerImpl(instance()) }
    install(Routing) {
        auth(authController)
    }
}