package com.icici.moneytransfer.util

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.icici.moneytransfer.BuildConfig
import com.icici.moneytransfer.domain.model.dashboard.balance.BranchDetailsItem
import com.icici.moneytransfer.domain.model.login_model.LoginResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object AppPreferences {

    private const val NAME = "com.info.icici.bank.imobile"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_LOGIN_PREF = Pair("is_login", false)
    private val IS_OTPVERIFY_PREF = Pair("is_otp_verify", false)
    private val USER_ID_PREF = Pair("user_id", 0)
    private val PINSET = Pair("SETPINAPP", "")
    private val LOGINDATA_PREF = Pair("logindataA_app", "")
    private val BRANCHESLIST_PREF = Pair("mybrancheslist_app", "")
    private val USER_TYPE_PREF = Pair("user_type", "")
    private val USER_MOBILE_PREF = Pair("user_mobileno", "")
    private val ALLOCAT_WARD_PREF = Pair("allocat_wards", "")
    private val ALLOCAT_ULB_PREF = Pair("allocat_ulb", "")
    private val cookies = Pair("cookies_app", "")
    private val cookies2 = Pair("cookies_app2", "")
    private val cookies3 = Pair("cookies_app3", "")
    private val userID = Pair("userID", "")
    private val PASSWORD = Pair("PASSWORD", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isOTPVerify: Boolean
        get() = preferences.getBoolean(IS_OTPVERIFY_PREF.first, IS_OTPVERIFY_PREF.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_OTPVERIFY_PREF.first, value)
        }

    var isLogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN_PREF.first, IS_LOGIN_PREF.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN_PREF.first, value)
        }

    var userId: Int
        get() = preferences.getInt(USER_ID_PREF.first, USER_ID_PREF.second)
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putInt(USER_ID_PREF.first, value)
        }

    var logindata: LoginResponse?
        get() {
            val strLoginResponse = preferences.getString(LOGINDATA_PREF.first, LOGINDATA_PREF.second)

            val res = if(strLoginResponse==""){
                return  null
            }else
                Gson().fromJson(strLoginResponse, LoginResponse::class.java)

            return res
        }
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(LOGINDATA_PREF.first,   Gson().toJson(value))
        }
    var myBarnchesList: List<BranchDetailsItem?>?
        get() {
            val strLoginResponse = preferences.getString(BRANCHESLIST_PREF.first, BRANCHESLIST_PREF.second)

            val res = if(strLoginResponse==""){
                return  null
            }else {

                val listType: Type = object : TypeToken<List<BranchDetailsItem?>?>() {}.type
                return Gson().fromJson(strLoginResponse, listType)
            }
               // Gson().fromJson(strLoginResponse, LoginResponse::class.java)

            return res
        }
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(BRANCHESLIST_PREF.first,   Gson().toJson(value))
        }

    var userType: String?
        get() = preferences.getString(USER_TYPE_PREF.first, USER_TYPE_PREF.second)
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(USER_TYPE_PREF.first, value)
        }

    var userMobileNo: String?
        get() = preferences.getString(USER_MOBILE_PREF.first, USER_MOBILE_PREF.second)
        set(value) = preferences.edit {
            it.putString(USER_MOBILE_PREF.first, value)
        }

    var allocatedWardstr: String?
        get() = preferences.getString(ALLOCAT_WARD_PREF.first, ALLOCAT_WARD_PREF.second)
        set(value) = preferences.edit {
            it.putString(ALLOCAT_WARD_PREF.first, value)
        }

    var cookiesApp: String?
        get() = preferences.getString(cookies.first, cookies.second)
        set(value) = preferences.edit {
            it.putString(cookies.first, value)
        }

    var cookiesApp2: String?
        get() = preferences.getString(cookies2.first, cookies2.second)
        set(value) = preferences.edit {
            it.putString(cookies2.first, value)
        }

    var cookiesApp3: String?
        get() = preferences.getString(cookies3.first, cookies3.second)
        set(value) = preferences.edit {
            it.putString(cookies3.first, value)
        }

    var mpin: String?
        get() = preferences.getString(PINSET.first, PINSET.second)
        set(value) = preferences.edit {
            it.putString(PINSET.first, value)
        }

    var pass: String?
        get() = preferences.getString(PASSWORD.first, PASSWORD.second)
        set(value) = preferences.edit {
            it.putString(PASSWORD.first, value)
        }

    val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
}