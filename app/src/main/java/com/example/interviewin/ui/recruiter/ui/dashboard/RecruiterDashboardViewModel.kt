package com.example.interviewin.ui.recruiter.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository

class RecruiterDashboardViewModel(private val repository: DataRepository): ViewModel() {

    fun getJobs() = repository.getJob()
}