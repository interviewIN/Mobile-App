package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class ChatResponse(

	@field:SerializedName("interviewId")
	val interviewId: Int,

	@field:SerializedName("chat")
	val chat: List<ChatItem>
)

data class ChatItem(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("answer")
	var answer: String
)
