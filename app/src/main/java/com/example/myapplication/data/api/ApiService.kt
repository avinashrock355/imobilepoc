package com.example.myapplication.data.api

import android.content.Context
import android.net.ConnectivityManager
import com.example.myapplication.data.api.ApiRoutes.baseurl
import com.example.myapplication.data.api.ApiRoutes.login
import com.example.myapplication.data.api.ApiRoutes.netBalance
import com.example.myapplication.data.api.ApiRoutes.oneTimeFT
import com.example.myapplication.domain.model.CodeRefrenceResponseItem
//import com.example.myapplication.domain.model.LoginRequest
//import com.example.myapplication.domain.model.dashboard.NetbalanceReq
//import com.example.myapplication.domain.model.dashboard.balance.NetBalanceResponse
import com.example.myapplication.domain.model.fundtransfer.*
//import com.example.myapplication.domain.model.login_model.LoginResponse
import com.example.myapplication.domain.model.payee.BankBranchResponseItem
import com.icici.moneytransfer.domain.model.LoginRequest
import com.icici.moneytransfer.domain.model.dashboard.NetbalanceReq
import com.icici.moneytransfer.domain.model.dashboard.balance.NetBalanceResponse
import com.icici.moneytransfer.domain.model.login_model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService   {


    @POST(login)
    fun loginService(@Header("IV")  iv:String, @Body mLoginRequest: LoginRequest): Call<LoginResponse>?

    @POST(netBalance)
    fun getNetBalance( @Body mNetbalanceReq: NetbalanceReq): Call<NetBalanceResponse>?

    @POST(ApiRoutes.recentTransactions)
    fun recentTransactions( @Body mNetbalanceReq: NetbalanceReq): Call< List<RecentTransactionsResponseItem?>>?


    @POST(ApiRoutes.ftPayeeList)
    fun ftPayeeList( @Body mNetbalanceReq: NetbalanceReq): Call< List<PayeeListResponseItem?>>?

    @POST(ApiRoutes.ftCheckBalance)
    fun ftCheckBalance( @Body mNetbalanceReq: NetbalanceReq): Call<CheckBalResponse>?

    @POST(ApiRoutes.scheduleTransaction)
    fun scheduleTransaction( @Body mNetbalanceReq: NetbalanceReq): Call<List<ScheduleTLResponseItem?>? >?

    @POST(ApiRoutes.completedTransaction)
    fun completedTransaction( @Body req: CompltedTranResquest): Call<List<CompletedFTResponseItem?>?>?

    @POST(oneTimeFT)
    fun callOneTimeFT( @Body req: OneTimeFundTransferRequest): Call<OneTimeIntiateResponse>?

    @POST(oneTimeFT)
    fun callOneTimeFTConfirmationMode( @Body req: OneTimeFundTransferRequest): Call<OneTimeFTResponse>?

    @POST(ApiRoutes.impsIfscFundTransfer)
    fun impsIfscFundTransfer( @Body req: OneTimeFundTransferRequest): Call<OneTimeIntiateResponse>?

    @POST(ApiRoutes.impsIfscFundTransfer)
    fun impsIfscFTConfirmationMode( @Body req: OneTimeFundTransferRequest): Call<OneTimeFTResponse>?

    @POST(ApiRoutes.getBankBranchDetails)
    fun getBankBranchDetails( @Body req: NetbalanceReq): Call<List<BankBranchResponseItem?>?>?

    @POST(ApiRoutes.codeReference)
    fun codeReference( @Body req: NetbalanceReq): Call<List<CodeRefrenceResponseItem?>?>?

    @POST(ApiRoutes.accountDetail)
    fun accountDetail( @Body req: AccountDetailRequest): Call<List<AccountDetailsResponseItem?>?>?

    @POST(ApiRoutes.iciciQuickFT)
    fun iciciQuickFT( @Body req: OneTimeFundTransferRequest): Call<OneTimeIntiateResponse>?

    @POST(ApiRoutes.iciciQuickFT)
    fun quickFTConfirmationMode( @Body req: OneTimeFundTransferRequest): Call<OneTimeFTResponse>?

}


object Api {

    fun chkStatus(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (connMgr.activeNetworkInfo != null
                && connMgr.activeNetworkInfo!!.isAvailable
                && connMgr.activeNetworkInfo!!.isConnected)
    }
    //val immageURL1=immageURL
    val retrofitService: ApiService by lazy {
        RetrofitClient().getRetrofitClient(null,baseurl).create(ApiService::class.java)
    }
}