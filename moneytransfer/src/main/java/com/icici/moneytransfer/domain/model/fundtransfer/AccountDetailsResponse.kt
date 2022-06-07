package com.icici.moneytransfer.domain.model.fundtransfer

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AccountDetailsResponse(

	@field:SerializedName("AccountDetailsResponse")
	val accountDetailsResponse: List<AccountDetailsResponseItem?>? = null
)


data class AccountCurrency(

	@field:SerializedName("codeType")
	val codeType: String? = null,

	@field:SerializedName("codeDescription")
	val codeDescription: String? = null,

	@field:SerializedName("cmCode")
	val cmCode: String? = null
)

data class AccountDetailsResponseItem (

	@field:SerializedName("branchCode")
	val branchCode: String? = null,

	@field:SerializedName("accountId")
	val accountId: String? = null,

	@field:SerializedName("accountType")
	val accountType: AccountType? = null,

	@field:SerializedName("accountNickname")
	val accountNickname: String? = null,

	@field:SerializedName("accountCurrency")
	val accountCurrency: AccountCurrency? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		TODO("accountType"),
		parcel.readString(),
		TODO("accountCurrency")
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(branchCode)
		parcel.writeString(accountId)
		parcel.writeString(accountNickname)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<AccountDetailsResponseItem> {
		override fun createFromParcel(parcel: Parcel): AccountDetailsResponseItem {
			return AccountDetailsResponseItem(parcel)
		}

		override fun newArray(size: Int): Array<AccountDetailsResponseItem?> {
			return arrayOfNulls(size)
		}
	}
}
