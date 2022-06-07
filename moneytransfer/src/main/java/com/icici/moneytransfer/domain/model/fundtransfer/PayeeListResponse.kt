package com.icici.moneytransfer.domain.model.fundtransfer

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PayeeListResponse(

	@field:SerializedName("PayeeListResponse")
	val payeeListResponse: List<PayeeListResponseItem?>? = null
)

data class PayeeListResponseItem(

	@field:SerializedName("counterPartyNickName")
	val counterPartyNickName: String? = null,

	@field:SerializedName("accountID")
	val accountID: String? = null,

	@field:SerializedName("hostConsumerCode")
	val hostConsumerCode: String? = null,

	@field:SerializedName("payeeListID")
	val payeeListID: String? = null,

	@field:SerializedName("beneficiaryType")
	val beneficiaryType: String? = null,

	@field:SerializedName("counterPartyID")
	val counterPartyID: String? = null,

	@field:SerializedName("consumerCode")
	val consumerCode: String? = null,

	@field:SerializedName("ifscCode")
	val ifscCode: String? = null,

	@field:SerializedName("counterPartyRegID")
	val counterPartyRegID: String? = null


) :Parcelable{
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun toString(): String {
		return "$ifscCode"
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(counterPartyNickName)
		parcel.writeString(accountID)
		parcel.writeString(hostConsumerCode)
		parcel.writeString(payeeListID)
		parcel.writeString(beneficiaryType)
		parcel.writeString(counterPartyID)
		parcel.writeString(consumerCode)
		parcel.writeString(ifscCode)
		parcel.writeString(counterPartyRegID)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<PayeeListResponseItem> {
		override fun createFromParcel(parcel: Parcel): PayeeListResponseItem {
			return PayeeListResponseItem(parcel)
		}

		override fun newArray(size: Int): Array<PayeeListResponseItem?> {
			return arrayOfNulls(size)
		}
	}
}
