package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class GetJobResponse(
	@field:SerializedName("jobs")
	val jobs: List<JobsItem>
)

data class JobsItem(

	@SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("companyId")
	val companyId: Int,

	@field:SerializedName("company")
	val company: Company,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("interviewQuestions")
	val interviewQuestions: List<String>,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("applied")
	val applied: Boolean
)

