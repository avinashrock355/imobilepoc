package com.icici.moneytransfer.fundtransfer

import android.annotation.SuppressLint
import android.util.Log
import android.util.Pair
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icici.moneytransfer.data.api.ApiRoutes
import com.icici.moneytransfer.data.api.ApiService
import com.icici.moneytransfer.data.api.RetrofitClient
import com.icici.moneytransfer.data.repository.DashboardRepositoryImp
import com.icici.moneytransfer.data.repository.FTRepositoryImp
import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.dashboard.NetbalanceReq
import com.icici.moneytransfer.domain.model.fundtransfer.AccountDetailRequest
import com.icici.moneytransfer.domain.model.fundtransfer.CompltedTranResquest
import com.icici.moneytransfer.domain.model.fundtransfer.OneTimeFundTransferRequest
import com.icici.moneytransfer.domain.model.fundtransfer.TransactionDate
import com.icici.moneytransfer.util.AppPreferences
import java.util.ArrayList

class FundTransferViewModel : ViewModel() {


   // val reponseError = MutableLiveData<String>()
    lateinit var mResult: MutableLiveData<Result>
    private val retrofitClient = RetrofitClient()
    private var apiService: ApiService
    private val mFTRepositoryImp: FTRepositoryImp

    init {
        val headerList = ArrayList<Pair<String, String>>()
        headerList.add(Pair("Content-Type", "application/json"))
        headerList.add(Pair("XSRF-TOKEN", AppPreferences.logindata?.xsrfToken))
        headerList.add(Pair("Cookie", "${AppPreferences.cookiesApp}"))
        headerList.add(Pair("Cookie", "${AppPreferences.cookiesApp2}"))
        headerList.add(Pair("Cookie", "${AppPreferences.cookiesApp3}"))
        headerList.add(Pair("Cookie", "device_id=7"))
        apiService = retrofitClient.getRetrofitClient(headerList, ApiRoutes.baseurl)
            .create(ApiService::class.java)
        mFTRepositoryImp = FTRepositoryImp(apiService)
    }


    fun getRecentTransactions() {
        mResult = mFTRepositoryImp.recentTransactions(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                userId = AppPreferences.logindata?.user!!.userId
            )
        )

    }


    fun getBankBranchDetails(ifsc:String) {
        mResult = mFTRepositoryImp.getBankBranchDetails(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                routingNumber ="$ifsc"
            )
        )

    }
    fun getBankBranchDetailsBYBANKNAME(bankname:String) {
        mResult = mFTRepositoryImp.getBankBranchDetails(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                bankName ="$bankname"
            )
        )

    }


    fun codeReference() {
        mResult = mFTRepositoryImp.codeReference(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                code_type = "GBK"
            )
        )

    }
    fun accountDetail() {
        mResult = mFTRepositoryImp.accountDetail(
            AccountDetailRequest(
                bankId = ApiRoutes.bankID,
                transactionType = "TPF",
                userId =  AppPreferences.logindata?.user!!.userId,
                billerId = "",
                requestId = ""
            )
        )

    }

    fun completedTransaction( d:TransactionDate) {

        mResult = mFTRepositoryImp.completedTransaction(
           CompltedTranResquest(
               bankId = ApiRoutes.bankID,
               userId = AppPreferences.logindata?.user!!.userId,
               //fromAccountId = AppPreferences.myBarnchesList!![0]!!.accountNumber,
               fromAccountId = "000401198080",
               transactionCurrency = ApiRoutes.currency,
               transactionDate = d,
               transactionType = "TPF"
           )
        )

    }
    fun callOneTimeFT( d: OneTimeFundTransferRequest) {

        mResult = mFTRepositoryImp.callOneTimeFT(d)

    }

    fun quickFT( d: OneTimeFundTransferRequest) {

        mResult = mFTRepositoryImp.quickFT(d)

    }
    fun callOneTimeFTConfirmationMode( d: OneTimeFundTransferRequest) {

        mResult = mFTRepositoryImp.callOneTimeFTConfirmationMode(d)

    }

    fun quickFTConfirmationMode( d: OneTimeFundTransferRequest) {

        mResult = mFTRepositoryImp.quickFTConfirmationMode(d)

    }
    fun impsIfscFTConfirmationMode( d: OneTimeFundTransferRequest) {

        mResult = mFTRepositoryImp.impsIfscFTConfirmationMode(d)

    }
    fun impsIfscFundTransfer( d: OneTimeFundTransferRequest) {

        mResult = mFTRepositoryImp.impsIfscFundTransfer(d)

    }
    fun getscheduleTransactionList() {
        mResult = mFTRepositoryImp.scheduleTransaction(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                userId = AppPreferences.logindata?.user!!.userId
            )
        )

    }

    fun getftPayeeList() {
        mResult = mFTRepositoryImp.ftPayeeList(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                userId = AppPreferences.logindata?.user!!.userId
            )
        )

    }

    fun getftCheckBalance( accountNo:String) {
        mResult = mFTRepositoryImp.ftCheckBalance(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                userId = AppPreferences.logindata?.user!!.userId,
                accountId = accountNo//AppPreferences.myBarnchesList?.get(0)!!.accountNumber
            )
        )

    }
}