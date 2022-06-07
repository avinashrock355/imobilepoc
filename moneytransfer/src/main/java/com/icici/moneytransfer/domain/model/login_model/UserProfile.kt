package com.icici.moneytransfer.domain.model.login_model

import com.google.gson.annotations.SerializedName

data class UserProfile(

	@field:SerializedName("widgetVisibility")
	val widgetVisibility: String,

	@field:SerializedName("commonDetails")
	val commonDetails: String,

	@field:SerializedName("userProfiledetails")
	val userProfiledetails: UserProfiledetails,

	@field:SerializedName("fedId")
	val fedId: String,

	@field:SerializedName("deviceId")
	val deviceId: String
)