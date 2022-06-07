package com.example.myapplication.domain.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(

	@field:SerializedName("device_id")
	val deviceId: String? = null,

	@field:SerializedName("machine_fingerprint")
	val machineFingerprint: String? = null,

	@field:SerializedName("device_type")
	val deviceType: String? = null,

	@field:SerializedName("language_id")
	val languageId: String? = null,

	@field:SerializedName("client_id")
	val clientId: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("grant_type")
	val grantType: String? = null,

	@field:SerializedName("bank_id")
	val bankId: String? = null,

	@field:SerializedName("loginEventTime")
	val loginEventTime: String? = null,

	@field:SerializedName("client_secret")
	val clientSecret: String? = null,

	@field:SerializedName("channel_id")
	val channelId: String? = null,

	@field:SerializedName("custom_data")
	val customData: Any? = null,

	@field:SerializedName("username")
	val username: String? = null
)
