package com.example.myapplication.domain.model.fundtransfer

import com.google.gson.annotations.SerializedName

data class CheckBalnceStatusResponse(

	@field:SerializedName("message")
	val message: List<MessageItem?>? = null
)

data class MessageItem(

	@field:SerializedName("nameSpaceInfo")
	val nameSpaceInfo: String? = null,

	@field:SerializedName("messageAddlnInfo")
	val messageAddlnInfo: String? = null,

	@field:SerializedName("messageDesc")
	val messageDesc: String? = null,

	@field:SerializedName("messageCode")
	val messageCode: String? = null,

	@field:SerializedName("message_TYPE")
	val messageTYPE: String? = null
)
