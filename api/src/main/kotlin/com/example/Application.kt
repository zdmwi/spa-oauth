package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.config.di
import com.example.controllers.AuthControllerImpl
import com.example.controllers.auth
import com.example.models.User
import com.example.services.UserServiceImpl
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.util.*
import io.ktor.util.url
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

    val loginProviders = listOf(
        OAuthServerSettings.OAuth2ServerSettings(
            name = "github",
            authorizeUrl = "https://github.com/login/oauth/authorize",
            accessTokenUrl = "https://github.com/login/oauth/access_token",
            clientId = environment.config.property("oauth.githubClientId").getString(),
            clientSecret = environment.config.property("oauth.githubClientSecret").getString(),
            defaultScopes = listOf("read:user", "user:email")
        )
    ).associateBy { it.name }

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
                    val userService by di.newInstance { UserServiceImpl(instance()) }
                    userService.findById(credential.payload.getClaim("id").asInt())
                } else null
            }
        }
        oauth("github-oauth") {
            client = HttpClient(CIO)
            providerLookup = { loginProviders["github"] }
            urlProvider = { "http://localhost:4288/auth/login/oauth/github/callback" }
        }
    }

    val authController by di.newInstance { AuthControllerImpl(instance(), instance(), instance()) }
    install(Routing) {
        auth(authController)
    }

    routing {
        authenticate("auth-jwt") {
            get("/whoami") {
                val principal = call.authentication.principal<User>()
                val subjectString = principal!!.id.toString()
                call.respondText(subjectString)
            }
        }
    }
}