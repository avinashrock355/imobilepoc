package com.example.myapplication

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
//import com.icici.moneytransfer.util.AppPreferences
import com.icici.moneytransfer.util.AppPreferences

open class MyApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)

    }
}