package com.icici.moneytransfer.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class AccountDetailRequest(

	@field:SerializedName("transactionType")
	val transactionType: String? = null,

	@field:SerializedName("billerId")
	val billerId: String? = null,

	@field:SerializedName("bankId")
	val bankId: String? = null,

	@field:SerializedName("requestId")
	val requestId: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
