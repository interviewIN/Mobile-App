package com.example.interviewin.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interviewin.data.DataRepository
import com.example.interviewin.di.Injection
import com.example.interviewin.ui.MainViewModel
import com.example.interviewin.ui.auth.login.LoginViewModel
import com.example.interviewin.ui.auth.register.RegisterViewModel
import com.example.interviewin.ui.candidate.chat.ChatViewModel
import com.example.interviewin.ui.candidate.dashboard.DashboardViewModel
import com.example.interviewin.ui.candidate.interview.InterviewViewModel
import com.example.interviewin.ui.recruiter.ui.addJob.AddJobViewModel
import com.example.interviewin.ui.recruiter.ui.appliedlist.AppliedListViewModel
import com.example.interviewin.ui.recruiter.ui.dashboard.RecruiterDashboardViewModel
import com.example.interviewin.ui.recruiter.ui.interview.RecruiterInterviewViewModel
import com.example.interviewin.ui.recruiter.ui.profile.RecruiterProfileViewModel
import com.example.interviewin.ui.recruiter.ui.summary.SummaryViewModel

class ViewModelFactory(private val repository: DataRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(repository) as T
            }

            modelClass.isAssignableFrom(InterviewViewModel::class.java) -> {
                InterviewViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RecruiterDashboardViewModel::class.java) -> {
                RecruiterDashboardViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RecruiterInterviewViewModel::class.java) -> {
                RecruiterInterviewViewModel(repository) as T
            }

            modelClass.isAssignableFrom(AppliedListViewModel::class.java) -> {
                AppliedListViewModel(repository) as T
            }

            modelClass.isAssignableFrom(AddJobViewModel::class.java) -> {
                AddJobViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ChatViewModel::class.java) -> {
                ChatViewModel(repository) as T
            }

            modelClass.isAssignableFrom(SummaryViewModel::class.java) -> {
                SummaryViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RecruiterProfileViewModel::class.java) -> {
                RecruiterProfileViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class " + modelClass.name)
        }
    }

    companion object{
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            return ViewModelFactory(Injection.provideRepository(context))
        }
    }
}