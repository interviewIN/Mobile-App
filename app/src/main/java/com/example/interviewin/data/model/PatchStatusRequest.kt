package com.example.interviewin.data.model

import com.google.gson.annotations.SerializedName

data class PatchStatusRequest(
    @field:SerializedName("interviewId")
    val interviewId: Int,

    @field:SerializedName("status")
    val status: String
)
