package com.icici.moneytransfer.domain.model.login_model

import com.google.gson.annotations.SerializedName

data class AmountFormat(

	@field:SerializedName("codeType")
	val codeType: String,

	@field:SerializedName("codeDescription")
	val codeDescription: String,

	@field:SerializedName("cmCode")
	val cmCode: String
)