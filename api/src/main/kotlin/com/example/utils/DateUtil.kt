package com.example.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun convertDateToLocalDateTime(date: Date): LocalDateTime {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
}