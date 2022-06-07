package com.icici.moneytransfer.domain.model.login_model

import com.google.gson.annotations.SerializedName

data class UserProfiledetails(

	@field:SerializedName("dateFormat")
	val dateFormat: DateFormat,

	@field:SerializedName("userProfileSess")
	val userProfileSess: Any,

	@field:SerializedName("mobileNumber")
	val mobileNumber: String,

	@field:SerializedName("language")
	val language: Language,

	@field:SerializedName("multiCurrencyTxnAllowed")
	val multiCurrencyTxnAllowed: MultiCurrencyTxnAllowed,

	@field:SerializedName("rangeLimitScheme")
	val rangeLimitScheme: RangeLimitScheme,

	@field:SerializedName("logoffDate")
	val logoffDate: String,

	@field:SerializedName("userNickName")
	val userNickName: String,

	@field:SerializedName("loginAllowed")
	val loginAllowed: LoginAllowed,

	@field:SerializedName("transactionAuthorizationScheme")
	val transactionAuthorizationScheme: TransactionAuthorizationScheme,

	@field:SerializedName("preferredTimeZone")
	val preferredTimeZone: Any,

	@field:SerializedName("lastUnsuccessfullLoginChannel")
	val lastUnsuccessfullLoginChannel: LastUnsuccessfullLoginChannel,

	@field:SerializedName("accessScheme")
	val accessScheme: AccessScheme,

	@field:SerializedName("transactionAllowed")
	val transactionAllowed: TransactionAllowed,

	@field:SerializedName("firstName")
	val firstName: String,

	@field:SerializedName("bankId")
	val bankId: String,

	@field:SerializedName("phoneNumber")
	val phoneNumber: Any,

	@field:SerializedName("accountMaskingRequired")
	val accountMaskingRequired: AccountMaskingRequired,

	@field:SerializedName("lastUnsuccessfullLoginTime")
	val lastUnsuccessfullLoginTime: String,

	@field:SerializedName("userType")
	val userType: UserType,

	@field:SerializedName("salutation")
	val salutation: Salutation,

	@field:SerializedName("userRoleId")
	val userRoleId: Any,

	@field:SerializedName("unifiedLoginUser")
	val unifiedLoginUser: UnifiedLoginUser,

	@field:SerializedName("status")
	val status: Any,

	@field:SerializedName("lastName")
	val lastName: String,

	@field:SerializedName("lastSuccessfullLoginTime")
	val lastSuccessfullLoginTime: Any,

	@field:SerializedName("accountFormat")
	val accountFormat: AccountFormat,

	@field:SerializedName("amountFormat")
	val amountFormat: AmountFormat,

	@field:SerializedName("loginDate")
	val loginDate: String,

	@field:SerializedName("principalId")
	val principalId: String,

	@field:SerializedName("orgId")
	val orgId: String,

	@field:SerializedName("prospectIndicator")
	val prospectIndicator: ProspectIndicator,

	@field:SerializedName("primaryBranchId")
	val primaryBranchId: Any,

	@field:SerializedName("lastTransactionDate")
	val lastTransactionDate: String,

	@field:SerializedName("divisionAccessIndicator")
	val divisionAccessIndicator: DivisionAccessIndicator,

	@field:SerializedName("customerId")
	val customerId: String,

	@field:SerializedName("primaryDivisionId")
	val primaryDivisionId: Any,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("confidentialTransactionAccess")
	val confidentialTransactionAccess: ConfidentialTransactionAccess,

	@field:SerializedName("userRoleName")
	val userRoleName: Any,

	@field:SerializedName("calendarType")
	val calendarType: CalendarType,

	@field:SerializedName("loginChannel")
	val loginChannel: LoginChannel,

	@field:SerializedName("outOfOfficePreference")
	val outOfOfficePreference: OutOfOfficePreference,

	@field:SerializedName("categoryCode")
	val categoryCode: CategoryCode,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("segmentName")
	val segmentName: SegmentName,

	@field:SerializedName("limitScheme")
	val limitScheme: LimitScheme,

	@field:SerializedName("primaryAccountId")
	val primaryAccountId: String,

	@field:SerializedName("middleName")
	val middleName: Any
)