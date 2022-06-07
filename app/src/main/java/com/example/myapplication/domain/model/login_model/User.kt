package com.example.myapplication.domain.model.login_model

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("isUnifiedLoginAllowed")
	val isUnifiedLoginAllowed: String,

	@field:SerializedName("isTermsandConditionsAcceptanceRequired")
	val isTermsandConditionsAcceptanceRequired: String,

	@field:SerializedName("forceSecurityQuestionChangeFlag")
	val forceSecurityQuestionChangeFlag: String,

	@field:SerializedName("forceSignonPasswordChangeFlag")
	val forceSignonPasswordChangeFlag: String,

	@field:SerializedName("forceTxnPasswordChangeFlag")
	val forceTxnPasswordChangeFlag: String,

	@field:SerializedName("forceModeChangeFlag")
	val forceModeChangeFlag: String,

	@field:SerializedName("userType")
	val userType: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("uniqueId")
	val uniqueId: String,

	@field:SerializedName("forceUSSDPinChangeFlag")
	val forceUSSDPinChangeFlag: String,

	@field:SerializedName("corporateId")
	val corporateId: String
)