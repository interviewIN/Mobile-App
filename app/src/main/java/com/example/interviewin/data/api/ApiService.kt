package com.example.interviewin.data.api

import com.example.interviewin.data.api.response.LoginResponse
import com.example.interviewin.data.api.response.RegisterResponse
import com.example.interviewin.data.model.LoginRequest
import com.example.interviewin.data.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}