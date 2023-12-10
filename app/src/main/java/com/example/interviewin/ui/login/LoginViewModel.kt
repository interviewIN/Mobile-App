package com.example.interviewin.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.api.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: DataRepository): ViewModel() {
    fun login(username: String, password: String) = repository.login(username, password)

    fun saveSession(token: String) {
        viewModelScope.launch {
            repository.saveSession(token)
        }
    }
}