package com.example.interviewin.data

import androidx.lifecycle.liveData
import com.example.interviewin.data.api.ApiService
import com.example.interviewin.data.api.response.ChatResponse
import com.example.interviewin.data.api.response.LoginResponse
import com.example.interviewin.data.api.response.MessageResponse
import com.example.interviewin.data.api.response.RegisterResponse
import com.example.interviewin.data.model.ApplyJobRequest
import com.example.interviewin.data.model.JobRequest
import com.example.interviewin.data.model.LoginRequest
import com.example.interviewin.data.model.PatchStatusRequest
import com.example.interviewin.data.model.RegisterRequest
import com.example.interviewin.data.model.UserModel
import com.example.interviewin.data.preference.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class DataRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    fun register(request: RegisterRequest) = liveData {
        emit(ResultState.Loading)
        try {
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

    fun postJob(request: JobRequest) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.postJob(request)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getJob() = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.getJobs()
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun applyJob(request: ApplyJobRequest) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.applyJob(request)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getInterviews() = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.getInterviews()
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getInterviewsByJob(jobId: Int) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.getInterviewByJob(jobId)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun patchStatus(request: PatchStatusRequest) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.patchStatus(request)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun generateFirstQuestion(chat: ChatResponse) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.generateFirstQuestion(chat)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun generateQuestion(chat: ChatResponse) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.generateQuestion(chat)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getChat(interviewId: Int) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.getChat(interviewId)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getInterviewById(id: Int) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.getInterviewById(id)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getUser() = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.getUser()
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
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