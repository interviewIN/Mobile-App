package com.example.interviewin.ui.recruiter.ui.addJob

import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.model.JobRequest

class AddJobViewModel(private val repository: DataRepository): ViewModel() {
    fun postJob(request: JobRequest) = repository.postJob(request)
}