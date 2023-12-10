package com.example.interviewin.data

import androidx.lifecycle.liveData
import com.example.interviewin.data.api.ApiService
import com.example.interviewin.data.api.response.LoginResponse
import com.example.interviewin.data.api.response.RegisterResponse
import com.example.interviewin.data.model.LoginRequest
import com.example.interviewin.data.model.RegisterRequest
import com.example.interviewin.data.preference.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class DataRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    fun register(username: String, password: String, role: String?) = liveData {
        emit(ResultState.Loading)
        try {
            val request = RegisterRequest(username, password, role)
            val successResponse = apiService.register(request)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun login(username: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val request = LoginRequest(username, password)
            val successResponse = apiService.login(request)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    suspend fun saveSession(token: String) {
        userPreference.saveSession(token)
    }

    fun getSession(): Flow<String> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }
    companion object{
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): DataRepository = DataRepository(userPreference, apiService)
    }
}