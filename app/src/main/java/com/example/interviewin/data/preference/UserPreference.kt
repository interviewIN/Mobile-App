package com.example.interviewin.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.interviewin.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class UserPreference private constructor(
    private val dataStore: DataStore<Preferences>
){
    suspend fun saveSession(user: UserModel) {
        dataStore.edit {
            it[USERNAME_KEY] = user.username
            it[ROLE_KEY] = user.role
            it[TOKEN_KEY] = user.token
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map {
             UserModel(
                 it[USERNAME_KEY] ?: "",
                 it[ROLE_KEY] ?: "",
                 it[TOKEN_KEY] ?: "",
             )
        }
    }

    suspend fun logout() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val ROLE_KEY = stringPreferencesKey("role")
        private val TOKEN_KEY = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference{
            return UserPreference(dataStore)
        }
    }
}