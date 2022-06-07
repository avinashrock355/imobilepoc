package com.icici.moneytransfer.domain.repository


import androidx.lifecycle.LiveData
import com.icici.moneytransfer.domain.model.LoginRequest
import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.login_model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body

/**
 *
 * */
interface LoginRepository {
    fun loginService(iv:String,@Body mLoginRequest: LoginRequest): LiveData<Result/*Result.Success<LoginResponse>*/>
}
