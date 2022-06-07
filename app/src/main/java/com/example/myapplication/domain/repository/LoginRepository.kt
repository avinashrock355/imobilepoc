package com.example.myapplication.domain.repository


import androidx.lifecycle.LiveData
//import com.example.myapplication.domain.model.LoginRequest
import com.example.myapplication.domain.model.Result
import com.example.myapplication.domain.model.login_model.LoginResponse
import com.icici.moneytransfer.domain.model.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body

/**
 *
 * */
interface LoginRepository {
    fun loginService(iv:String,@Body mLoginRequest: LoginRequest): LiveData<Result/*Result.Success<LoginResponse>*/>
}
