package com.romainpiel.readerview.utils

import java.util.regex.Pattern

object Patterns {
    val HTTP_URL = Pattern.compile("(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
}

fun String.findFirstUrl(): String? {
    val matcher = Patterns.HTTP_URL.matcher(this)
    if (matcher.find()) {
        return matcher.group(0)
    }
    return null
}