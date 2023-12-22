package com.example.interviewin.ui.candidate.chat

import androidx.lifecycle.ViewModel
import com.example.interviewin.data.DataRepository
import com.example.interviewin.data.api.response.ChatResponse

class ChatViewModel(private val repository: DataRepository): ViewModel() {
    fun generateFirstQuestion(chat: ChatResponse) = repository.generateFirstQuestion(chat)
    fun getChat(interviewId: Int) = repository.getChat(interviewId)
}