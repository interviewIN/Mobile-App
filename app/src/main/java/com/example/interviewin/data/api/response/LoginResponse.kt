package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("message")
    val message: String
)