package com.icici.moneytransfer.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class CheckBalResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

/*

	@field:SerializedName("status")
	val status: CheckBalnceStatusResponse? = null
*/


)

data class AvailableAccountBalance(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)

data class DataItem(

	@field:SerializedName("availableAccountBalance")
	val availableAccountBalance: AvailableAccountBalance? = null,

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("accountType")
	val accountType: AccountType? = null
)

data class AccountType(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)
