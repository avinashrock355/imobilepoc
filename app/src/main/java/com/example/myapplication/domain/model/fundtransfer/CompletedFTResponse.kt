package com.example.myapplication.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class CompletedFTResponse(

	@field:SerializedName("CompletedFTResponse")
	val completedFTResponse: List<CompletedFTResponseItem?>? = null
)

data class CompletedFTResponseItem(

	@field:SerializedName("numberOfEntries")
	val numberOfEntries: Int? = null,

	@field:SerializedName("transactionCurrency")
	val transactionCurrency: TransactionCurrency? = null,

	@field:SerializedName("transactionStatus")
	val transactionStatus: TransactionStatus? = null,

	@field:SerializedName("entityType")
	val entityType: EntityType? = null,

	@field:SerializedName("transactionDate")
	val transactionDate: String? = null,

	@field:SerializedName("toAccountNickname")
	val toAccountNickname: String? = null,

	@field:SerializedName("referenceId")
	val referenceId: String? = null,

	@field:SerializedName("transactionId")
	val transactionId: Int? = null,

	@field:SerializedName("transactionType")
	val transactionType: TransactionType? = null,

	@field:SerializedName("totalTransactionAmount")
	val totalTransactionAmount: Double? = null,

	@field:SerializedName("frequencyType")
	val frequencyType: FrequencyType? = null,

	@field:SerializedName("markedForStop")
	val markedForStop: String? = null,

	@field:SerializedName("onHoldFlag")
	val onHoldFlag: String? = null
)
