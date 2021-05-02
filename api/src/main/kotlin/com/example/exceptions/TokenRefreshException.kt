package com.example.exceptions

data class TokenRefreshException(val msg: String): Exception(msg)
