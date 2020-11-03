package com.innocent.chuck.core.extensions

fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")