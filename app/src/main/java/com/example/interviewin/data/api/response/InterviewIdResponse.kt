package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class InterviewIdResponse(

	@field:SerializedName("interview")
	val interview: InterviewId
)

data class InterviewId(

	@field:SerializedName("summary")
	val summary: Any,

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

	@field:SerializedName("job")
	val job: Job,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

