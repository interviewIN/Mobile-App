package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class InterviewIdResponse(

	@field:SerializedName("interview")
	val interview: InterviewId
)

data class Summary(

	@field:SerializedName("technicalCapability")
	val technicalCapability: String,

	@field:SerializedName("interviewId")
	val interviewId: Int,

	@field:SerializedName("personalCapability")
	val personalCapability: String,

	@field:SerializedName("psychologicalCapability")
	val psychologicalCapability: String,

	@field:SerializedName("mostRelevantPosition")
	val mostRelevantPosition: String,

	@field:SerializedName("chanceOfGettingTheJob")
	val chanceOfGettingTheJob: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overallImpression")
	val overallImpression: String,

	@field:SerializedName("finalThoughts")
	val finalThoughts: String
)

data class InterviewId(

	@field:SerializedName("summary")
	val summary: Summary,

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

	@field:SerializedName("job")
	val job: Job,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)