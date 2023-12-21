package com.example.interviewin.ui.recruiter.ui.appliedlist

import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.model.PatchStatusRequest

class AppliedListViewModel(private val repository: DataRepository): ViewModel() {
    fun getInterviewByJob(jobId: Int) = repository.getInterviewsByJob(jobId)

    fun patchStatus(request: PatchStatusRequest) = repository.patchStatus(request)
}