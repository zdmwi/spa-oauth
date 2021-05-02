package com.example.utils

import org.mindrot.jbcrypt.BCrypt

fun hashPassword(clearText: String): String {
    return BCrypt.hashpw(clearText, BCrypt.gensalt())
}

fun checkPassword(plaintextPassword: String, hashedPassword: String): Boolean {
    return BCrypt.checkpw(plaintextPassword, hashedPassword)
}