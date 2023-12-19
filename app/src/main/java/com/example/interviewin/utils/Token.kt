package com.example.interviewin.utils

import android.util.Base64
import com.example.interviewin.data.model.TokenModel
import com.google.gson.Gson

object Token {
    fun decodeToken(token: String): TokenModel? {
        val parts = token.split(".")
        if (parts.size == 3) {
            val payload = parts[1]
            val decodedPayload = String(Base64.decode(payload, Base64.URL_SAFE), Charsets.UTF_8)
            return Gson().fromJson(decodedPayload, TokenModel::class.java)
        }
        return null
    }
}