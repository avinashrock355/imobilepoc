package com.example.myapplication.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class OneTimeFundTransferRequest 	(

	@field:SerializedName("headerData")
	var headerData: HeaderData? = null,

	@field:SerializedName("transactionDetails")
	var transactionDetails: TransactionDetailsFT? = null,

	@field:SerializedName("bankId")
	var bankId: String? = null,

	@field:SerializedName("payeeName")
	var payeeName: String? = null,

	@field:SerializedName("additionalDetails")
	var additionalDetails: AdditionalDetailsFT? = null,

	@field:SerializedName("customTransactionDetails")
	var customTransactionDetails: CustomTransactionDetails? = null,

	@field:SerializedName("userId")
	var userId: String? = null
)

data class CustomTransactionDetails(

	@field:SerializedName("payeeListId")
	var payeeListId: String? = null
)

data class AdditionalDetailsFT(

	@field:SerializedName("transactionCurrency")
	var transactionCurrency: String? = null,

	@field:SerializedName("frequencyType")
	var frequencyType: String? = null,

	@field:SerializedName("transactionRemarks")
	var transactionRemarks: String? = null
)

data class TransactionDetailsFT(

	@field:SerializedName("transactionType")
	var transactionType: String? = null,

	@field:SerializedName("payeeNickName")
	var payeeNickName: String? = null,

	@field:SerializedName("payeeName")
	var payeeName: String? = null,

	@field:SerializedName("payeeNetwork")
	var payeeNetwork: String? = null,

	@field:SerializedName("fromAccountId")
	var fromAccountId: String? = null,

	@field:SerializedName("transactionAmount")
	var transactionAmount: Double? = null,

	@field:SerializedName("payeeBank")
	var payeeBank: String? = null,

	@field:SerializedName("toAccountId")
	var toAccountId: String? = null,

	@field:SerializedName("transactionDate")
	var transactionDate: String? = null,

	@field:SerializedName("networkType")
	var networkType: String? = null,

	@field:SerializedName("payeeBankIdentifier")
	var payeeBankIdentifier: String? = null
)

data class HeaderData(

	@field:SerializedName("deviceType")
	var deviceType: String? = null,

	@field:SerializedName("isConfirmationMode")
	var isConfirmationMode: String? = null,

	@field:SerializedName("ipAddress")
	var ipAddress: String? = null,

	@field:SerializedName("machineFingerPrint")
	var machineFingerPrint: String? = null,

	@field:SerializedName("deviceId")
	var deviceId: String? = null
)
