package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class UpdatedInterviewResponse(

	@field:SerializedName("updatedInterview")
	val updatedInterview: UpdatedInterview
)

data class UpdatedInterview(

	@field:SerializedName("jobId")
	val jobId: Int,

	@field:SerializedName("questions")
	val questions: List<String>,

	@field:SerializedName("answers")
	val answers: List<String>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("candidateId")
	val candidateId: Int,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
