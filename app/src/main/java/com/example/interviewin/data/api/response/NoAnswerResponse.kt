package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class NoAnswerResponse(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("status")
	val status: String
)
