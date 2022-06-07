package com.example.myapplication.presentation.login

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.System
import android.telephony.SmsManager
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.domain.model.Result
import com.example.myapplication.domain.model.login_model.ErrorLoginResponse
//import com.example.myapplication.domain.model.login_model.LoginResponse
import com.example.myapplication.presentation.dashboard.DashboardActivity
import com.icici.moneytransfer.util.AppPreferences
import com.example.myapplication.util.sim.DualSimManager
import com.example.myapplication.util.toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.icici.moneytransfer.domain.model.login_model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executor


class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        bioMatricLogin()
        listener()

        if (AppPreferences.mpin != "" && AppPreferences.isLogin && AppPreferences.logindata != null) {
            cardviewMPIN.visibility = View.VISIBLE
            cardview.visibility = View.INVISIBLE

            viewModel.strUserID.value = AppPreferences.logindata?.user!!.userId
            viewModel.strPassWord.value = AppPreferences.pass
            viewModel.isMPinSet.value = true

            fingerPrintLay.setOnClickListener {
                 biometricPrompt.authenticate(promptInfo)
             //   bottomSheetDialog()

                //this@LoginActivity.requestHint()
            }
        }
        binding.pinview.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.mPin.value=s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        /*
*/
//        binding.loginBTN.setOnClickListener {
//
//        }
        viewModel.reponseError.observe(this, Observer {
            it.toast(this@LoginActivity)
            Log.e("live data------", "onCreate: " + it)
        })

        //  passMObile.inputType= Text.InputTypes.TextVariationPassword or Text.InputTypes.ClassText
        /* passMObile.transformationMethod=HiddenPassTransformationMethod()
         passMObile.addTextChangedListener(object : TextWatcher{
             override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
 //                TODO("Not yet implemented")
             }

             override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 Log.e("TAG-----------", "onTextChanged: "+s )
             }

             override fun afterTextChanged(s: Editable?) {
   //              TODO("Not yet implemented")
             }
         })
 */
        // dialogSim()
        //registerReceiver()
    }

    private fun listener() {
        viewModel.mResult.observe(this@LoginActivity, Observer {
            dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    when (it.data) {
                        is String -> {
                            val loginResponse: String = it.data
                            showProgressDialog()
                        }
                        is LoginResponse -> {
                            val loginResponse: LoginResponse = it.data
                            AppPreferences.isLogin = true
                            AppPreferences.logindata = loginResponse
                            AppPreferences.pass = viewModel.strPassWord.value
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    DashboardActivity::class.java
                                )
                            )
                            finish()
                            Log.e("TAG-----", "onAuthenticationSucceeded: " + it.data.sessionId)
                            Log.e(
                                "TAG--------",
                                "onAuthenticationSucceeded: " + it.data.user.userId
                            )
                        }
                    }


                }
                is Result.Failure -> {
                    Toast.makeText(
                        applicationContext,
                        it.msg, Toast.LENGTH_SHORT
                    ).show()
                    //  Log.e("TAG-----------", "onAuthenticationSucceeded: error")
                }
                is Result.Error<*> -> {
                    val error = it.exception as ErrorLoginResponse?
                    Toast.makeText(
                        applicationContext,
                        error?.message!!, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun bioMatricLogin() {

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    viewModel.isFingerLogin.value=true
                    //showProgressDialog()
                    viewModel.doLogin()


                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("ICICI app")
            .setSubtitle("Log in using your biometric credential").setConfirmationRequired(true)
            .setNegativeButtonText("Use account password")
            .build()


    }

    lateinit var smsDialod: BottomSheetDialog
    fun bottomSheetDialog() {
        smsDialod = BottomSheetDialog(this)
        val bottomSheet = layoutInflater.inflate(R.layout.bottom_sheet, null)
        val sim1Lay = bottomSheet.findViewById<LinearLayout>(R.id.sim1Lay)
        val sim2Lay = bottomSheet.findViewById<LinearLayout>(R.id.sim2Lay)
        val sim1Img = bottomSheet.findViewById<ImageView>(R.id.sim1Img)
        val sim2Img = bottomSheet.findViewById<ImageView>(R.id.sim2Img)
        val sim1 = bottomSheet.findViewById<TextView>(R.id.sim1)
        val sim2 = bottomSheet.findViewById<TextView>(R.id.sim2)

        var localSubscriptionManager: SubscriptionManager? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            localSubscriptionManager = SubscriptionManager.from(this)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            if (localSubscriptionManager != null && localSubscriptionManager.activeSubscriptionInfoCount > 1) {
                val localList: List<*> = localSubscriptionManager.activeSubscriptionInfoList
                val simInfo1 = localList[0] as SubscriptionInfo
                val simInfo2 = localList[1] as SubscriptionInfo

                sim1.text = simInfo1.carrierName
                sim2.text = simInfo2.carrierName
            }
        }

        sim1Lay.setOnClickListener {
            // dialog.dismiss()
            sim1Img.setColorFilter(
                ContextCompat.getColor(this@LoginActivity, R.color.green),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            sim2Img.setColorFilter(
                ContextCompat.getColor(this@LoginActivity, R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            showProgressDialog()
            sendSMS("8770642149", "ICICI BANK Verification test message", "Sim1")
        }

        sim2Lay.setOnClickListener {
            //    dialog.dismiss()

            sim1Img.setColorFilter(
                ContextCompat.getColor(this@LoginActivity, R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            sim2Img.setColorFilter(
                ContextCompat.getColor(this@LoginActivity, R.color.green),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            showProgressDialog()
            sendSMS("8770642149", "ICICI BANK Verification test message", "Sim2")
        }
        bottomSheet.setOnClickListener { smsDialod.dismiss() }

        smsDialod.setContentView(bottomSheet)
        smsDialod.show()
    }

    private fun registerReceiver() {
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                var message: String? = null
                when (resultCode) {
                    RESULT_OK -> message = "Message Sent Successfully !"
                    SmsManager.RESULT_ERROR_GENERIC_FAILURE -> message = "Error."
                    SmsManager.RESULT_ERROR_NO_SERVICE -> message = "Error: No service."
                    SmsManager.RESULT_ERROR_NULL_PDU -> message = "Error: Null PDU."
                    SmsManager.RESULT_ERROR_RADIO_OFF -> message = "Error: Radio off."
                }
                message!!.toast(this@LoginActivity)
                dismissProgressDialog()
                if (smsDialod != null && smsDialod.isShowing) {
                    smsDialod.dismiss()
                }
                // smsStatus.setText(message);
            }
        }, IntentFilter(SMS_SENT_ACTION))


        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.e("TAG", "SMS Delivered: ")
                //tvIN.append("SMS Delivered: ")
                dismissProgressDialog()
                if (smsDialod != null && smsDialod.isShowing) {
                    smsDialod.dismiss()
                }
                "SMS Delivered ".toast(this@LoginActivity)
                //  deliveryStatus.setText("SMS Delivered");
            }
        }, IntentFilter(SMS_DELIVERED_ACTION))
    }


    fun openImportantNotices(view: View) {
        var rotate = 270.0f
        tvIN.visibility = if (tvIN.visibility == View.VISIBLE) {
            rotate = 90.0f
            View.GONE
        } else
            View.VISIBLE

        dropdownView.animate().rotation(rotate)
            .alpha(1.0f)
            .setListener(null);

        //layimportantnotices.visibility = layimportantnotices.visibility == View.VISIBLE ? View.GONE: View.VISIBLE
    }

    fun openDosDonts(view: View) {

        containerViewAll.visibility = if (containerViewAll.visibility == View.VISIBLE)
            View.GONE
        else
            View.VISIBLE
    }

}