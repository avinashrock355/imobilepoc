package com.icici.moneytransfer.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class OneTimeFTResponse(

	@field:SerializedName("transactionDetails")
	val transactionDetails: TransactionDetails? = null,

	@field:SerializedName("confirmationDetails")
	val confirmationDetails: ConfirmationDetails? = null,

	@field:SerializedName("additionalDetails")
	val additionalDetails: AdditionalDetails? = null
)

data class EntityType(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class TransactionCurrency(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class PayeeBank(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class TransactionDetails(

	@field:SerializedName("transactionType")
	val transactionType: TransactionType? = null,

	@field:SerializedName("payeeNetwork")
	val payeeNetwork: PayeeNetwork? = null,

	@field:SerializedName("payeeName")
	val payeeName: String? = null,

	@field:SerializedName("fromAccountId")
	val fromAccountId: String? = null,

	@field:SerializedName("transactionAmount")
	val transactionAmount: String? = null,

	@field:SerializedName("payeeBank")
	val payeeBank: PayeeBank? = null,

	@field:SerializedName("toAccountId")
	val toAccountId: String? = null,

	@field:SerializedName("transactionDate")
	val transactionDate: String? = null,

	@field:SerializedName("networkType")
	val networkType: NetworkType? = null,

	@field:SerializedName("payeeBankIdentifier")
	val payeeBankIdentifier: String? = null
)

data class AdditionalDetails(

	@field:SerializedName("transactionCurrency")
	val transactionCurrency: TransactionCurrency? = null,

	@field:SerializedName("entityType")
	val entityType: EntityType? = null,

	@field:SerializedName("frequencyType")
	val frequencyType: FrequencyType? = null,

	@field:SerializedName("transactionRemarks")
	val transactionRemarks: String? = null
)

data class TransactionType(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class FrequencyType(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class NetworkType(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class PayeeNetwork(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class ConfirmationDetails(

	@field:SerializedName("requestReferenceId")
	val requestReferenceId: String? = null,

	@field:SerializedName("transactionStatus")
	val transactionStatus: TransactionStatus? = null,

	@field:SerializedName("responseMessage")
	val responseMessage: String? = null,

	@field:SerializedName("transactionReferenceId")
	val transactionReferenceId: String? = null,

	@field:SerializedName("responseCode")
	val responseCode: String? = null,
	@field:SerializedName("hostReferenceId")
	val hostReferenceId: String? = null
)
