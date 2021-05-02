package com.example.config

import com.example.repositories.UserRepository
import com.example.repositories.UserRepositoryImpl
import com.example.services.AuthService
import com.example.services.AuthServiceImpl
import com.example.services.UserService
import com.example.services.UserServiceImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val authModule = DI.Module("auth") {
    bind<AuthService>() with provider { AuthServiceImpl(instance()) }
}

val userModule = DI.Module("user") {
    bind<UserService>() with provider { UserServiceImpl(instance()) }
    bind<UserRepository>() with provider { UserRepositoryImpl() }
}

val di = DI {
    import(authModule)
    import(userModule)
}