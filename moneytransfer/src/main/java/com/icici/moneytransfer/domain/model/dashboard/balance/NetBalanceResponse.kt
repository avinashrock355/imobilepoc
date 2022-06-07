package com.icici.moneytransfer.domain.model.dashboard.balance

import com.google.gson.annotations.SerializedName

data class NetBalanceResponse(

	@field:SerializedName("depositAccountCount")
	val depositAccountCount: Int? = null,

	@field:SerializedName("allDepositAccounts")
	val allDepositAccounts: AllDepositAccounts? = null,

	@field:SerializedName("allBankAccounts")
	val allBankAccounts: AllBankAccounts? = null,

	@field:SerializedName("totalAssets")
	val totalAssets: TotalAssets? = null,

	@field:SerializedName("totalLiabilities")
	val totalLiabilities: TotalLiabilities? = null,

	@field:SerializedName("bankAccountCount")
	val bankAccountCount: Int? = null,

	@field:SerializedName("currency")
	val currency: Currency? = null,

	@field:SerializedName("branchDetails")
	val branchDetails: List<BranchDetailsItem?>? = null
)

data class AllBankAccounts(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)

data class AllDepositAccounts(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)

data class BranchDetailsItem(

	@field:SerializedName("branchAddress")
	val branchAddress: Any? = null,

	@field:SerializedName("accountNumber")
	val accountNumber: String? = null,

	@field:SerializedName("ifscCode")
	val ifscCode: String? = null

) {
	override fun toString(): String {
		return "$accountNumber"
	}
}

data class Currency(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class TotalAssets(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)

data class TotalLiabilities(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)
