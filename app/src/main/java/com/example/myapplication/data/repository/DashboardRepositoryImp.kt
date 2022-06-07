package com.example.myapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.api.ApiService
import com.example.myapplication.domain.model.LoginRequest
import com.example.myapplication.domain.model.Result
//import com.example.myapplication.domain.model.dashboard.NetbalanceReq
//import com.example.myapplication.domain.model.dashboard.balance.NetBalanceResponse
import com.example.myapplication.domain.model.login_model.LoginResponse
import com.example.myapplication.domain.repository.DashboardRepository
import com.example.myapplication.domain.repository.LoginRepository
import com.icici.moneytransfer.domain.model.dashboard.NetbalanceReq
import com.icici.moneytransfer.domain.model.dashboard.balance.NetBalanceResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardRepositoryImp(
    private val retrofitService: ApiService
) : DashboardRepository {
    override fun getNetBalance(mNetbalanceReq: NetbalanceReq): MutableLiveData<Result> {
        val repoListLive = MutableLiveData<Result>()
        retrofitService.getNetBalance(mNetbalanceReq)?.enqueue(object :
            Callback<NetBalanceResponse> {
            override fun onResponse(call: Call<NetBalanceResponse>, response: Response<NetBalanceResponse>) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<NetBalanceResponse>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message);
            }
        })
        return repoListLive
    }

}