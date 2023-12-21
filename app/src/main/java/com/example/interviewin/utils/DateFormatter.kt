package com.example.interviewin.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatter {
    fun formatTime(inputDate: String): String {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val date = inputFormat.parse(inputDate)

            val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            return outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}