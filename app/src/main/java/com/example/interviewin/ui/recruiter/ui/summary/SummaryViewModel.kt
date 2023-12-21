package com.example.interviewin.ui.recruiter.ui.summary

import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository

class SummaryViewModel(private val repository: DataRepository): ViewModel() {
    fun getInterviewById(id: Int) = repository.getInterviewById(id)
}