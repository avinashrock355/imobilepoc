package com.example.myapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.api.ApiService
import com.example.myapplication.domain.model.CodeRefrenceResponseItem
import com.example.myapplication.domain.model.Result
//import com.example.myapplication.domain.model.dashboard.NetbalanceReq
import com.example.myapplication.domain.model.fundtransfer.*
import com.example.myapplication.domain.model.login_model.ErrorLoginResponse
import com.example.myapplication.domain.model.payee.BankBranchResponseItem
import com.example.myapplication.domain.repository.FTRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icici.moneytransfer.domain.model.dashboard.NetbalanceReq
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FTRepositoryImp(
    private val retrofitService: ApiService
) : FTRepository {
    val repoListLive = MutableLiveData<Result>()
    override fun recentTransactions(mNetbalanceReq: NetbalanceReq): MutableLiveData<Result> {
      //  val repoListLive = MutableLiveData<Result>()
        retrofitService.recentTransactions(mNetbalanceReq)?.enqueue(object :
            Callback< List<RecentTransactionsResponseItem?>> {
            override fun onResponse(
                call: Call< List<RecentTransactionsResponseItem?>>,
                response: Response< List<RecentTransactionsResponseItem?>>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call< List<RecentTransactionsResponseItem?>>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun scheduleTransaction(mNetbalanceReq: NetbalanceReq): MutableLiveData<Result> {

        retrofitService.scheduleTransaction(mNetbalanceReq)?.enqueue(object :
            Callback<List<ScheduleTLResponseItem?>? > {
            override fun onResponse(
                call: Call<List<ScheduleTLResponseItem?>? >,
                response: Response<List<ScheduleTLResponseItem?>? >
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<List<ScheduleTLResponseItem?>? >, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message);
            }
        })
        return repoListLive
    }

    override fun ftPayeeList(mNetbalanceReq: NetbalanceReq): MutableLiveData<Result> {

        retrofitService.ftPayeeList(mNetbalanceReq)?.enqueue(object :
            Callback< List<PayeeListResponseItem?>> {
            override fun onResponse(
                call: Call< List<PayeeListResponseItem?>>,
                response: Response< List<PayeeListResponseItem?>>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call< List<PayeeListResponseItem?>>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun ftCheckBalance(mNetbalanceReq: NetbalanceReq): MutableLiveData<Result> {

        retrofitService.ftCheckBalance(mNetbalanceReq)?.enqueue(object :
            Callback<CheckBalResponse> {
            override fun onResponse(
                call: Call<CheckBalResponse>,
                response: Response<CheckBalResponse>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<CheckBalResponse>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun completedTransaction(req: CompltedTranResquest): MutableLiveData<Result> {

        retrofitService.completedTransaction(req)?.enqueue(object :
            Callback<List<CompletedFTResponseItem?>?> {
            override fun onResponse(
                call: Call<List<CompletedFTResponseItem?>?>,
                response: Response<List<CompletedFTResponseItem?>?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<List<CompletedFTResponseItem?>?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun callOneTimeFT(req: OneTimeFundTransferRequest): MutableLiveData<Result> {
        //callOneTimeFT

        retrofitService.callOneTimeFT(req)?.enqueue(object :
            Callback<OneTimeIntiateResponse?> {
            override fun onResponse(
                call: Call<OneTimeIntiateResponse?>,
                response: Response<OneTimeIntiateResponse?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                }  else {
                    repoListLive.value = Result.Failure(response.message())
                    if(response.code()==400||response.code()==404) {
                        val mChannelContext =
                            response.headers().get("ChannelContext")?.replace("Path=/", "")
                        if (mChannelContext != null) {
                            val gson = Gson()
                            val type = object : TypeToken<OneTimeIntiateResponse>() {}.type
                            val errorResponse: OneTimeIntiateResponse? =
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

            override fun onFailure(call: Call<OneTimeIntiateResponse?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun callOneTimeFTConfirmationMode(req: OneTimeFundTransferRequest): MutableLiveData<Result> {

        retrofitService.callOneTimeFTConfirmationMode(req)?.enqueue(object :
            Callback<OneTimeFTResponse?> {
            override fun onResponse(
                call: Call<OneTimeFTResponse?>,
                response: Response<OneTimeFTResponse?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<OneTimeFTResponse?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun impsIfscFTConfirmationMode(req: OneTimeFundTransferRequest): MutableLiveData<Result> {

        retrofitService.impsIfscFTConfirmationMode(req)?.enqueue(object :
            Callback<OneTimeFTResponse?> {
            override fun onResponse(
                call: Call<OneTimeFTResponse?>,
                response: Response<OneTimeFTResponse?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<OneTimeFTResponse?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive

    }

    override fun impsIfscFundTransfer(req: OneTimeFundTransferRequest):MutableLiveData<Result> {

        retrofitService.impsIfscFundTransfer(req)?.enqueue(object :
            Callback<OneTimeIntiateResponse?> {
            override fun onResponse(
                call: Call<OneTimeIntiateResponse?>,
                response: Response<OneTimeIntiateResponse?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<OneTimeIntiateResponse?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun getBankBranchDetails(req: NetbalanceReq): MutableLiveData<Result> {

        retrofitService.getBankBranchDetails(req)?.enqueue(object :
            Callback<List<BankBranchResponseItem?>?> {
            override fun onResponse(
                call: Call<List<BankBranchResponseItem?>?>,
                response: Response<List<BankBranchResponseItem?>?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!,forApi = "BankBranchDetails")
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<List<BankBranchResponseItem?>?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive

    }

    override fun codeReference(req: NetbalanceReq): MutableLiveData<Result> {

        retrofitService.codeReference(req)?.enqueue(object :
            Callback<List<CodeRefrenceResponseItem?>?> {
            override fun onResponse(
                call: Call<List<CodeRefrenceResponseItem?>?>,
                response: Response<List<CodeRefrenceResponseItem?>?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!,forApi = "codeReference")
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<List<CodeRefrenceResponseItem?>?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive

    }

    override fun accountDetail(req: AccountDetailRequest): MutableLiveData<Result> {

        retrofitService.accountDetail(req)?.enqueue(object :
            Callback<List<AccountDetailsResponseItem?>?> {
            override fun onResponse(
                call: Call<List<AccountDetailsResponseItem?>?>,
                response: Response<List<AccountDetailsResponseItem?>?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!,forApi = "codeReference")
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<List<AccountDetailsResponseItem?>?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive

    }

    override fun quickFT(req: OneTimeFundTransferRequest): MutableLiveData<Result> {

        retrofitService.iciciQuickFT(req)?.enqueue(object :
            Callback<OneTimeIntiateResponse?> {
            override fun onResponse(
                call: Call<OneTimeIntiateResponse?>,
                response: Response<OneTimeIntiateResponse?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                }  else {
                    repoListLive.value = Result.Failure(response.message())
                    if(response.code()==400||response.code()==404) {
                        val mChannelContext =
                            response.headers().get("ChannelContext")?.replace("Path=/", "")
                        if (mChannelContext != null) {
                            val gson = Gson()
                            val type = object : TypeToken<OneTimeIntiateResponse>() {}.type
                            val errorResponse: OneTimeIntiateResponse? =
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

            override fun onFailure(call: Call<OneTimeIntiateResponse?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

    override fun quickFTConfirmationMode(req: OneTimeFundTransferRequest): MutableLiveData<Result> {

        retrofitService.quickFTConfirmationMode(req)?.enqueue(object :
            Callback<OneTimeFTResponse?> {
            override fun onResponse(
                call: Call<OneTimeFTResponse?>,
                response: Response<OneTimeFTResponse?>
            ) {
                if (response.isSuccessful) {
                    repoListLive.value = Result.Success(response.body()!!)
                } else {
                    repoListLive.value = Result.Failure(response.message())
                }
                //  repoListLive.value = "pass"

                Log.e("TAG----", "onResponse: " + response.isSuccessful)
            }

            override fun onFailure(call: Call<OneTimeFTResponse?>, t: Throwable) {
                repoListLive.value = Result.Failure(t.message!!)
                //repoListLive.value = "fail"
                Log.e("TAG----", "onFailure: " + t.message)
            }
        })
        return repoListLive
    }

}