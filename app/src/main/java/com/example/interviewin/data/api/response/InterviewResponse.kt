package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class InterviewResponse(

	@field:SerializedName("interviews")
	val interviews: List<InterviewsItem>
)

data class Company(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class InterviewsItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("job")
	val job: Job,

	@field:SerializedName("status")
	val status: String
)

data class Job(

	@field:SerializedName("company")
	val company: Company,

	@field:SerializedName("title")
	val title: String
)
