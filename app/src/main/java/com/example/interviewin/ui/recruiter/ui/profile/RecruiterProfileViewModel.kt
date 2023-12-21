package com.example.interviewin.ui.recruiter.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewin.data.DataRepository
import kotlinx.coroutines.launch

class RecruiterProfileViewModel(private val repository: DataRepository): ViewModel() {
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getUser() = repository.getUser()
}