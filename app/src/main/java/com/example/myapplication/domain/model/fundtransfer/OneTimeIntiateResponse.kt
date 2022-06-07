package com.example.myapplication.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class OneTimeIntiateResponse(

	@field:SerializedName("authorization")
	val authorization: Authorization? = null,

	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Status(

	@field:SerializedName("message")
	val message: List<MessageItem?>? = null
)

data class Authorization(

	@field:SerializedName("acceptTermsAndConditions")
	val acceptTermsAndConditions: String? = null,

	@field:SerializedName("isConfirmationMode")
	val isConfirmationMode: String? = null,

	@field:SerializedName("workFlowRequired")
	val workFlowRequired: String? = null,

	@field:SerializedName("userIdRequired")
	val userIdRequired: String? = null
)

data class Pagination(

	@field:SerializedName("numRecReturned")
	val numRecReturned: String? = null,

	@field:SerializedName("hasMoreRecords")
	val hasMoreRecords: String? = null
)
