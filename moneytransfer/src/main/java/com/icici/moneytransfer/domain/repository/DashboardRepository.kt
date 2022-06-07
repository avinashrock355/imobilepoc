package com.icici.moneytransfer.domain.repository

import androidx.lifecycle.LiveData
import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.dashboard.NetbalanceReq
import okhttp3.ResponseBody
import retrofit2.http.Body

interface DashboardRepository {
    fun getNetBalance( @Body mNetbalanceReq: NetbalanceReq): LiveData<Result>
}