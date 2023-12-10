package com.example.interviewin.ui.register

import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository

class RegisterViewModel(private val repository: DataRepository): ViewModel() {
    fun register(username: String, password: String, role: String?) = repository.register(username, password, role)
}