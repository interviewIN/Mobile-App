package com.example.interviewin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.model.UserModel

class MainViewModel(private val repository: DataRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}