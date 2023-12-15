package com.example.interviewin.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.model.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: DataRepository): ViewModel() {
    fun login(username: String, password: String) = repository.login(username, password)

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}