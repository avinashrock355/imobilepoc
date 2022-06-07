package com.icici.moneytransfer.domain.repository

import androidx.lifecycle.LiveData

import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.dashboard.NetbalanceReq
import com.icici.moneytransfer.domain.model.fundtransfer.AccountDetailRequest
import com.icici.moneytransfer.domain.model.fundtransfer.CompltedTranResquest
import com.icici.moneytransfer.domain.model.fundtransfer.OneTimeFundTransferRequest
import retrofit2.http.Body

interface FTRepository {

    fun recentTransactions( @Body mNetbalanceReq: NetbalanceReq): LiveData<Result>
    fun scheduleTransaction( @Body mNetbalanceReq: NetbalanceReq): LiveData<Result>
    fun ftPayeeList( @Body mNetbalanceReq: NetbalanceReq): LiveData<Result>
    fun ftCheckBalance( @Body mNetbalanceReq: NetbalanceReq): LiveData<Result>
    fun completedTransaction( @Body req: CompltedTranResquest): LiveData<Result>
    fun callOneTimeFT( @Body req: OneTimeFundTransferRequest): LiveData<Result>
    fun callOneTimeFTConfirmationMode( @Body req: OneTimeFundTransferRequest): LiveData<Result>
    fun impsIfscFTConfirmationMode( @Body req: OneTimeFundTransferRequest): LiveData<Result>
    fun impsIfscFundTransfer( @Body req: OneTimeFundTransferRequest): LiveData<Result>
    fun getBankBranchDetails( @Body req: NetbalanceReq): LiveData<Result>
    fun codeReference( @Body req: NetbalanceReq): LiveData<Result>
    fun accountDetail( @Body req: AccountDetailRequest): LiveData<Result>
    fun quickFT( @Body req: OneTimeFundTransferRequest): LiveData<Result>
    fun quickFTConfirmationMode( @Body req: OneTimeFundTransferRequest): LiveData<Result>
}