package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.config.di
import com.example.controllers.AuthControllerImpl
import com.example.controllers.auth
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
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

    val issuer = environment.config.property("jwt.domain").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val appRealm = environment.config.property("jwt.realm").getString()
    val secret = environment.config.property("jwt.secret").getString()

    install(Authentication) {
        jwt("auth-jwt") {
            realm = appRealm
            verifier(JWT
                .require(Algorithm.HMAC256(secret))
                .withAudience(audience)
                .withIssuer(issuer)
                .build())
            validate { credential ->
                if (credential.payload.audience.contains(audience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }

    val authController by di.newInstance { AuthControllerImpl(instance()) }
    install(Routing) {
        auth(authController)
    }
}