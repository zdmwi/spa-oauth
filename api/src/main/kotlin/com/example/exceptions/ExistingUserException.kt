package com.example.exceptions

data class ExistingUserException(val msg: String): Exception(msg)