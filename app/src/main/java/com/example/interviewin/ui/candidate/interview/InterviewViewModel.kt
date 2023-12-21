package com.example.interviewin.ui.candidate.interview

import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository

class InterviewViewModel(private val repository: DataRepository): ViewModel() {
    fun getInterviews() = repository.getInterviews()
}