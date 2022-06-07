package com.example.myapplication.domain.model.payee

import com.google.gson.annotations.SerializedName
/*
data class BankBranchResponse(

	@field:SerializedName("BankBranchResponse")
	val bankBranchResponse: List<BankBranchResponseItem?>? = null
)*/

data class NetworkType(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null
)

data class RoutingNumberDetailsItem(

	@field:SerializedName("routingNumber")
	val routingNumber: String? = null,

	@field:SerializedName("networkType")
	val networkType: NetworkType? = null
)

data class State(

	@field:SerializedName("countryCode")
	val countryCode: String? = null,

	@field:SerializedName("stateCode")
	val stateCode: String? = null,

	@field:SerializedName("stateDescription")
	val stateDescription: String? = null
)

data class Country(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class BankBranchResponseItem(

	@field:SerializedName("zipCode")
	val zipCode: String? = null,

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("bankRefNo")
	val bankRefNo: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("branchName")
	val branchName: String? = null,

	@field:SerializedName("bankName")
	val bankName: String? = null,

	@field:SerializedName("state")
	val state: State? = null,

	@field:SerializedName("routingNumberDetails")
	val routingNumberDetails: List<RoutingNumberDetailsItem?>? = null
)
