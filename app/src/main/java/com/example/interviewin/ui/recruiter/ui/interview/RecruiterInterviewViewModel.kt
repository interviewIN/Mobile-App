package com.example.interviewin.ui.recruiter.ui.interview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.model.UserModel

class RecruiterInterviewViewModel(private val repository: DataRepository): ViewModel() {

    fun getJobs() = repository.getJob()

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}