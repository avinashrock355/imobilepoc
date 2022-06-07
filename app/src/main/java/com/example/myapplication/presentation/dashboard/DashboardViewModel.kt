package com.example.myapplication.presentation.dashboard

import android.annotation.SuppressLint
import android.util.Log
import android.util.Pair
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.api.ApiRoutes
import com.example.myapplication.data.api.ApiService
import com.example.myapplication.data.api.RetrofitClient
import com.example.myapplication.data.repository.DashboardRepositoryImp
import com.example.myapplication.domain.model.Result
import com.icici.moneytransfer.domain.model.dashboard.NetbalanceReq
//import com.example.myapplication.domain.model.dashboard.dashboardNetbalanceReq
import com.icici.moneytransfer.util.AppPreferences
import okhttp3.Cookie
import java.util.*

class DashboardViewModel : ViewModel() {

    val reponseError = MutableLiveData<String>()
    lateinit var mResult: MutableLiveData<Result>

    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }


    @SuppressLint("SimpleDateFormat")
    fun getBalance() {
        val retrofitClient = RetrofitClient()
        val headerList = ArrayList<Pair<String, String>>()
        headerList.add(Pair("Content-Type", "application/json"))
        headerList.add(Pair("XSRF-TOKEN", AppPreferences.logindata?.xsrfToken))
        headerList.add(Pair("Cookie", "${AppPreferences.cookiesApp}"))
        headerList.add(Pair("Cookie", "${AppPreferences.cookiesApp2}"))
        headerList.add(Pair("Cookie", "${AppPreferences.cookiesApp3}"))
        headerList.add(Pair("Cookie", "device_id=7"))

        val apiService = retrofitClient.getRetrofitClient(headerList, ApiRoutes.baseurl)
            .create(ApiService::class.java)

        mResult = DashboardRepositoryImp(apiService).getNetBalance(
            NetbalanceReq(
                bankId = ApiRoutes.bankID,
                userId = AppPreferences.logindata?.user!!.userId
            )
        )

    }
}