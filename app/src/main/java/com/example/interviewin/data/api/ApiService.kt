package com.example.interviewin.data.api

import com.example.interviewin.data.api.response.ChatResponse
import com.example.interviewin.data.api.response.GetJobResponse
import com.example.interviewin.data.api.response.InterviewResponse
import com.example.interviewin.data.api.response.LoginResponse
import com.example.interviewin.data.api.response.MessageResponse
import com.example.interviewin.data.api.response.NoAnswerResponse
import com.example.interviewin.data.api.response.RegisterResponse
import com.example.interviewin.data.model.JobRequest
import com.example.interviewin.data.model.LoginRequest
import com.example.interviewin.data.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("jobs")
    suspend fun postJob(
        @Body request: JobRequest
    ): MessageResponse

    @GET("jobs")
    suspend fun getJobs(): GetJobResponse

    @GET("interview")
    suspend fun getInterviews(): InterviewResponse

    @POST("interview/generateQuestion")
    suspend fun generateFirstQuestion(
        @Body request: ChatResponse
    ): NoAnswerResponse

    @POST("interview/generateQuestion")
    suspend fun generateQuestion(
        @Body request: ChatResponse
    ): ChatResponse

    @GET("interview/{id}/chat")
    suspend fun getChat(
        @Path("id") id: Int
    ): ChatResponse
}