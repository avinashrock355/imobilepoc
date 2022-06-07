package com.icici.moneytransfer.domain.model.dashboard

import com.google.gson.annotations.SerializedName

data class NetbalanceReq(

	@field:SerializedName("bankId")
	val bankId: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("accountId")
	val accountId: String? = null,
	@field:SerializedName("routingNumber")
	val routingNumber: String? = null,
	@field:SerializedName("code_type")
	val code_type: String? = null,
	@field:SerializedName("bankName")
	val bankName: String? = null
)
