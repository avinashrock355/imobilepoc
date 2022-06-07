package com.icici.moneytransfer.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.icici.moneytransfer.data.api.ApiService
import com.icici.moneytransfer.domain.model.LoginRequest
import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.login_model.ErrorLoginResponse
import com.icici.moneytransfer.domain.model.login_model.LoginResponse
import com.icici.moneytransfer.domain.repository.LoginRepository
import com.icici.moneytransfer.util.AppPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginRepositoryImpl(
    private val retrofitService: ApiService
) : LoginRepository {
    val repoListLive = MutableLiveData<Result>()

    override fun loginService(iv: String, mLoginRequest: LoginRequest): MutableLiveData<Result> {
      //  val repoListLive = MutableLiveData<Result>()
       /* retrofitService.loginService(iv, mLoginRequest)?.enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)

                    val cfcf2c = response.headers().get("Set-Cookie")?.replace("Path=/","")
                    val ccookilist = response.headers().values("Set-Cookie")
                    for (vvv in ccookilist){
                        Log.e("---------cokkies ", "onResponse: "+vvv )

                     val dd=   vvv.split(" ")

                    }
                  val ll=  ccookilist.map {
                        val dd=   it.split(" ")
                        dd[0]
                    }

                    AppPreferences.cookiesApp = ll[0]
                    AppPreferences.cookiesApp2 = ll[1]
                    AppPreferences.cookiesApp3 = ll[2]
                    Log.e("TAG ------", "onResponse: cookies  ${cfcf2c}")
                } else {
                    repoListLive.value = Result.Failure(response.message())
                    if(response.code()==400) {
                        val mChannelContext =
                            response.headers().get("ChannelContext")?.replace("Path=/", "")
                        if (mChannelContext != null) {
                            val gson = Gson()
                            val type = object : TypeToken<ErrorLoginResponse>() {}.type
                            val errorResponse: ErrorLoginResponse? =
                                gson.fromJson(mChannelContext, type)
                            repoListLive.value = Result.Error(errorResponse)
                            Log.e("--------- 400", "onResponse: " + errorResponse)
                        }
                        response.errorBody()!!.string()
                    }
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message);
            }
        })*/
        return repoListLive
    }

}