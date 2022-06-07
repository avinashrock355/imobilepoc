package com.example.myapplication.presentation.splash

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.presentation.login.LoginActivity
import com.example.myapplication.presentation.login.MobileVerificationActivity
import com.icici.moneytransfer.util.AppPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class Splash  : BaseActivity() {

    lateinit var mAlertDialog:AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

    }

    override fun onStart() {
        super.onStart()
        object : CountDownTimer(3000, 500) {
            override fun onFinish() {
                if (AppPreferences.isLogin&&AppPreferences.userId>0) {
                    //startActivity(Intent(this@Splash, DashboardActivity::class.java))
                    finish()
                    return
                }else if(AppPreferences.mpin != "" && AppPreferences.isLogin && AppPreferences.logindata !=null){
                    startActivity(Intent(this@Splash, LoginActivity::class.java))
                    finish()
                    return
                }
                startActivity(Intent(this@Splash, MobileVerificationActivity::class.java))
                finish()

            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }.start()
    }
}