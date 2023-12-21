package com.example.interviewin.data.api.response

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
