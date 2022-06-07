package com.example.myapplication.domain.model.login_model

data class ErrorLoginResponse(
	val code: Int? = null,
	val errorDescription: ErrorDescription? = null,
	val message: String? = null,
	val error: String? = null
)

data class ErrorDescription(
	val nameSpaceInfo: String? = null,
	val messageAddlnInfo: Any? = null,
	val messageDesc: String? = null,
	val messageCode: String? = null,
	val messageTYPE: String? = null
)

