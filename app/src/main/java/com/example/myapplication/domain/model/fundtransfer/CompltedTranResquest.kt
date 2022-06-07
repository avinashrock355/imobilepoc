package com.example.myapplication.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class CompltedTranResquest(

	@field:SerializedName("transactionType")
	val transactionType: String? = null,

	@field:SerializedName("bankId")
	val bankId: String? = null,

	@field:SerializedName("transactionCurrency")
	val transactionCurrency: String? = null,

	@field:SerializedName("fromAccountId")
	val fromAccountId: String? = null,

	@field:SerializedName("transactionDate")
	val transactionDate: TransactionDate? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)

data class TransactionDate(

	@field:SerializedName("lt")
	val lt: String? = null,

	@field:SerializedName("gt")
	val gt: String? = null
)
