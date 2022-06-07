package com.example.myapplication.domain.model

import com.google.gson.annotations.SerializedName

data class CodeRefrenceResponse(

	@field:SerializedName("CodeRefrenceResponse")
	val codeRefrenceResponse: List<CodeRefrenceResponseItem?>? = null
)

data class CodeRefrenceResponseItem(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null


) {
	override fun toString(): String {
		return "$codeDescription"
	}
}
