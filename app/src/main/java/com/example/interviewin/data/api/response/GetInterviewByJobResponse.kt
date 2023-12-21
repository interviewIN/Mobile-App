package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class GetInterviewByJobResponse(

	@field:SerializedName("interviews")
	val interviews: List<Interviews>
)

data class Interviews(

	@field:SerializedName("jobId")
	val jobId: Int,

	@field:SerializedName("questions")
	val questions: List<String>,

	@field:SerializedName("answers")
	val answers: List<Any>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("candidateId")
	val candidateId: Int,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
