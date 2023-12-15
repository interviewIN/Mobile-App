package com.example.interviewin.ui.auth.register

import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.model.RegisterRequest

class RegisterViewModel(private val repository: DataRepository): ViewModel() {
    fun register(request: RegisterRequest) = repository.register(request)
}