package com.example.interviewin.data.model

data class LoginRequest(
    val username: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val name: String,
    val email: String,
    val password: String,
    val role: String? = ""
)
