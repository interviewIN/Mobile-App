package com.example.interviewin.di

import android.content.Context
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.api.ApiConfig
import com.example.interviewin.data.preference.UserPreference
import com.example.interviewin.data.preference.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return DataRepository.getInstance(pref, apiService)
    }
}