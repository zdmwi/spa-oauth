package com.example.config

import com.example.services.AuthService
import com.example.services.AuthServiceImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.provider

val authDI = DI.Module("auth") {
    bind<AuthService>() with provider { AuthServiceImpl() }
}

val di = DI {
    import(authDI)
}