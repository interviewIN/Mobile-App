package com.example.interviewin.di

import android.content.Context
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.api.ApiConfig
import com.example.interviewin.data.preference.UserPreference
import com.example.interviewin.data.preference.dataStore

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return DataRepository.getInstance(pref, apiService)
    }
}