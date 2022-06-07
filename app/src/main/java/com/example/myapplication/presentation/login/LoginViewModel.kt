package com.example.myapplication.presentation.login

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.util.Log
import android.util.Pair
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.api.ApiRoutes
import com.example.myapplication.data.api.ApiService
import com.example.myapplication.data.api.RetrofitClient
import com.example.myapplication.data.repository.FTRepositoryImp
import com.example.myapplication.data.repository.LoginRepositoryImpl
//import com.example.myapplication.domain.model.LoginRequest
import com.example.myapplication.domain.model.Result
import com.icici.moneytransfer.util.AppPreferences
import com.icici.moneytransfer.util.AppPreferences.mpin
import com.example.myapplication.util.UtilClass
import com.example.myapplication.util.UtilClass.encrypt
import com.icici.moneytransfer.domain.model.LoginRequest
import java.util.*

class LoginViewModel : ViewModel() {

    val reponseError = MutableLiveData<String>()
    var mResult: MutableLiveData<Result>
    var strUserID = MutableLiveData<String>()
    var strPassWord = MutableLiveData<String>()
    var isMPinSet = MutableLiveData<Boolean>()
    var isFingerLogin = MutableLiveData<Boolean>()
    var mPin = MutableLiveData<String>()

    private val retrofitClient = RetrofitClient()
    private var apiService: ApiService
    private val mLoginRepositoryImpl: LoginRepositoryImpl
    private var randomKey16digit: String = UtilClass.randomString()

    init {
        isMPinSet.value = false
        isFingerLogin.value = false
        val headerList = ArrayList<Pair<String, String>>()
        headerList.add(Pair("Content-Type", "application/json"))
        headerList.add(Pair("IV", randomKey16digit));
        apiService = retrofitClient.getRetrofitClient(headerList, ApiRoutes.baseurl)
            .create(ApiService::class.java)
        mLoginRepositoryImpl = LoginRepositoryImpl(apiService)
        mResult = mLoginRepositoryImpl.repoListLive
    }

    fun doMpinLogin() {


    }

    @SuppressLint("SimpleDateFormat")
    fun doLogin() {
        if (isMPinSet.value!!&&!isFingerLogin.value!!) {
            if (mPin.value.isNullOrEmpty()) {
                reponseError.value = "Please Enter Mpin"
                return
            } else if (mPin.value != mpin!!) {
                reponseError.value = "Please Enter correct Mpin"
                return
            }
        }
        if (strUserID.value.isNullOrEmpty()) {
            reponseError.value = "Please Enter User Id"
            return
        } else if (strPassWord.value.isNullOrEmpty()) {
            reponseError.value = "Please Enter Password"
            return
        }

        val currentDateandTime = DateFormat.format("MM-dd-yyyy hh:mm:ss a", Date())
        val encPass = encrypt(/*"Deh@1234"*/strPassWord.value, randomKey16digit, ApiRoutes.encKey)
        val loginEventTimeenc =
            encrypt(currentDateandTime as String?, randomKey16digit, ApiRoutes.encKey)

        mResult.value = Result.Success("showdialog")
        mLoginRepositoryImpl.loginService(
            iv = randomKey16digit, mLoginRequest = LoginRequest(
                username = strUserID.value,//"558222302",//""558222302",
                password = encPass,
                loginEventTime = loginEventTimeenc,
                bankId = ApiRoutes.bankID,
                grantType = "password",
                languageId = "001",
                deviceType = "8",
                deviceId = "7",
                machineFingerprint = "eyJWRVJTSU9OIjoiMi4xLjIiLCJNRlAiOnsiQnJvd3NlciI6eyJVc2VyQWdlbnQiOiJNb3ppbGxhLzUuMCAoV2luZG93cyBOVCAxMC4wOyBXaW42NDsgeDY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvOTguMC40NzU4LjgwIFNhZmFyaS81MzcuMzYgRWRnLzk4LjAuMTEwOC41MCIsIlZlbmRvciI6Ikdvb2dsZSBJbmMuIiwiVmVuZG9yU3ViSUQiOiIiLCJCdWlsZElEIjoiMjAwMzAxMDciLCJDb29raWVFbmFibGVkIjp0cnVlfSwiSUVQbHVnaW5zIjp7fSwiTmV0c2NhcGVQbHVnaW5zIjp7IlBERiBWaWV3ZXIiOiIiLCJDaHJvbWUgUERGIFZpZXdlciI6IiIsIkNocm9taXVtIFBERiBWaWV3ZXIiOiIiLCJNaWNyb3NvZnQgRWRnZSBQREYgVmlld2VyIjoiIiwiV2ViS2l0IGJ1aWx0LWluIFBERiI6IiJ9LCJTY3JlZW4iOnsiRnVsbEhlaWdodCI6NzIwLCJBdmxIZWlnaHQiOjY4MCwiRnVsbFdpZHRoIjoxMjgwLCJBdmxXaWR0aCI6MTI4MCwiQ29sb3JEZXB0aCI6MjQsIlBpeGVsRGVwdGgiOjI0fSwiU3lzdGVtIjp7IlBsYXRmb3JtIjoiV2luMzIiLCJzeXN0ZW1MYW5ndWFnZSI6ImVuLVVTIiwiVGltZXpvbmUiOi0zMzB9fSwiRXh0ZXJuYWxJUCI6IiIsIk1FU0MiOnsibWVzYyI6Im1pPTI7Y2Q9MTUwO2lkPTQ1O21lc2M9NDY1Mzk3O21lc2M9NDQ3MjYxIn19",
                channelId = "",
                clientSecret = "",
                customData = null,
                clientId = ""
            )
        )
        isFingerLogin.value=false

    }
}