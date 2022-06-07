package com.example.myapplication.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class RecentTransactionsResponse(

	@field:SerializedName("RecentTransactionsResponse")
	val recentTransactionsResponse: List<RecentTransactionsResponseItem?>? = null
)

data class RecentTransactionsResponseItem(

	@field:SerializedName("payeeName")
	val payeeName: String? = null,

	@field:SerializedName("accountId")
	val accountId: String? = null,

	@field:SerializedName("bankIdentifier")
	val bankIdentifier: String? = null,

	@field:SerializedName("counterPartyId")
	val counterPartyId: String? = null,

	@field:SerializedName("consumerCode")
	val consumerCode: String? = null,

	@field:SerializedName("payeeNicName")
	val payeeNicName: String? = null,

	@field:SerializedName("tranAmount")
	val tranAmount: TranAmount? = null,

	@field:SerializedName("tranType")
	val tranType: String? = null
)

data class TranAmount(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)
