package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("message")
    val message: String
)
