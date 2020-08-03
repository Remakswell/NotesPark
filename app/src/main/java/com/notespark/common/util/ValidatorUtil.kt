package com.notespark.common.util

import java.util.regex.Pattern

object ValidatorUtil {
    fun isEmailValid(email: String) = Pattern
        .compile("^[a-zA-Z0-9]+@[a-z]+\\.[a-z]{2,4}\$")
        .matcher(email)
        .matches()

    fun isPasswordValid(password: String) = Pattern
        .compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$")
        .matcher(password)
        .matches()
}