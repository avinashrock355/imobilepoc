package com.example.myapplication.domain.model.login_model

import com.google.gson.annotations.SerializedName

data class MenuProfileItem(

	@field:SerializedName("menuIdOrder")
	val menuIdOrder: String,

	@field:SerializedName("parentMenuId")
	val parentMenuId: String,

	@field:SerializedName("menuId")
	val menuId: String,

	@field:SerializedName("menuIdType")
	val menuIdType: String,

	@field:SerializedName("menuText")
	val menuText: String
)