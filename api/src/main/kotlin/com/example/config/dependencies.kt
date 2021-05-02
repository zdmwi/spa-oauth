package com.example.config

import com.example.repositories.UserRepository
import com.example.repositories.UserRepositoryImpl
import com.example.services.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val authModule = DI.Module("auth") {
    bind<AuthService>() with provider { AuthServiceImpl(instance()) }
    bind<RefreshTokenService>() with provider { RefreshTokenServiceImpl(instance()) }
}

val userModule = DI.Module("user") {
    bind<UserService>() with provider { UserServiceImpl(instance()) }
    bind<UserRepository>() with provider { UserRepositoryImpl() }
}

val di = DI {
    import(authModule)
    import(userModule)
}