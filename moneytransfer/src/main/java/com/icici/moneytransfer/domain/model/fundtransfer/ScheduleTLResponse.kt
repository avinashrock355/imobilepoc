package com.icici.moneytransfer.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName
/*

data class ScheduleTLResponse(

	@field:SerializedName("ScheduleTLResponse")
	val scheduleTLResponse: List<ScheduleTLResponseItem?>? = null
)
*/

data class TransactionStatus(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class CounterpartyType(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)


data class HostFrequencyId(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class ScheduleTLResponseItem(

	@field:SerializedName("transactionType")
	val transactionType: TransactionType? = null,

	@field:SerializedName("isBbpsCheck")
	val isBbpsCheck: String? = null,

	@field:SerializedName("counterpartyID")
	val counterpartyID: String? = null,

	@field:SerializedName("totalTransactionAmount")
	val totalTransactionAmount: Double? = null,

	@field:SerializedName("numberOfInstallments")
	val numberOfInstallments: Int? = null,

	@field:SerializedName("transactionStatus")
	val transactionStatus: TransactionStatus? = null,

	@field:SerializedName("hostFrequencyId")
	val hostFrequencyId: HostFrequencyId? = null,

	@field:SerializedName("frequencyType")
	val frequencyType: FrequencyType? = null,

	@field:SerializedName("accountNickname")
	val accountNickname: String? = null,

	@field:SerializedName("counterpartyType")
	val counterpartyType: CounterpartyType? = null,

	@field:SerializedName("transactionDate")
	val transactionDate: String? = null,

	@field:SerializedName("hostReferenceNum")
	val hostReferenceNum: String? = null
)
