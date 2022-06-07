package com.icici.moneytransfer.domain.model.login_model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("menuProfile")
	val menuProfile: List<MenuProfileItem>,

	@field:SerializedName("xsrfToken")
	val xsrfToken: String,

	@field:SerializedName("sessionId")
	val sessionId: String,

	@field:SerializedName("token_type")
	val tokenType: String,

	@field:SerializedName("expires_in")
	val expiresIn: String,

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("userProfile")
	val userProfile: UserProfile
)