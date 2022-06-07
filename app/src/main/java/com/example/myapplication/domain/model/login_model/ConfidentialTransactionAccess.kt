package com.example.myapplication.domain.model.login_model

import com.google.gson.annotations.SerializedName

data class ConfidentialTransactionAccess(

	@field:SerializedName("codeType")
	val codeType: String,

	@field:SerializedName("codeDescription")
	val codeDescription: String,

	@field:SerializedName("cmCode")
	val cmCode: String
)