package com.example.interviewin.ui.candidate.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.model.ApplyJobRequest
import com.example.interviewin.data.model.UserModel

class DashboardViewModel(private val repository: DataRepository): ViewModel() {
    fun getJobs() = repository.getJob()

    fun applyJob(request: ApplyJobRequest) = repository.applyJob(request)
}