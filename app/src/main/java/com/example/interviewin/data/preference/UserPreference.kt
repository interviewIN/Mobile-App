package com.example.interviewin.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.interviewin.data.api.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference private constructor(
    private val dataStore: DataStore<Preferences>
){
    suspend fun saveSession(token: String) {
        dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }

    fun getSession(): Flow<String> {
        return dataStore.data.map {
             it[TOKEN_KEY] ?: ""
        }
    }

    suspend fun logout() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference{
            return UserPreference(dataStore)
        }
    }
}