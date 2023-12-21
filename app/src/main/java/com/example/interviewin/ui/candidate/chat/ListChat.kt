package com.example.interviewin.ui.candidate.chat

sealed class ListChat {
    data class UserChat(
        val username: String,
        val message: String
    ) : ListChat()

    data class BotChat(
        val message: String
    ) : ListChat()
}