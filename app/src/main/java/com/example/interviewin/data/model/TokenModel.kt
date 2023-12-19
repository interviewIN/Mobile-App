package com.example.interviewin.data.model

import com.google.gson.annotations.SerializedName

data class TokenModel(

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("iat")
    val iat: Int
)