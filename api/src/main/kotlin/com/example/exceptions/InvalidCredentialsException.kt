package com.example.exceptions

data class InvalidCredentialsException(val msg: String): Exception(msg)