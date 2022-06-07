package com.example.myapplication.presentation.login

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.icici.moneytransfer.util.AppPreferences
import com.example.myapplication.util.toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.act_otp_verify.*
import kotlinx.android.synthetic.main.activity_mobile_verification.*
import kotlinx.android.synthetic.main.activity_welcome.*

class MobileVerificationActivity : BaseActivity() {
    lateinit var smsDialod: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        continueBtn.setOnClickListener {

            if(layoutSMSVarification.visibility== View.VISIBLE){
                bottomSheetDialog()
              //  continueBtn.visibility = View.GONE
            }else{
                layoutSMSVarification.visibility = View.VISIBLE
            }
            //continueBtn.visibility = View.GONE
        }

        setPinBtn.setOnClickListener {
            pinview.text
            pincnf.text
            if (pinview.text.toString() != pincnf.text.toString()) {
                Log.e("-------- notvalid", "${pinview.text}onCreate: ${pincnf.text}")

                "MPIN not matched".toast(this@MobileVerificationActivity)
            }else{
                AppPreferences.mpin=pinview.text.toString()
                startActivity(Intent(this@MobileVerificationActivity,LoginActivity::class.java))
                finish()
            }
            Log.e("-------- MobileVerif", "${pinview.text}onCreate: ${pincnf.text}")
        }
        registerReceiver()
    }
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
                ContextCompat.getColor(this@MobileVerificationActivity, R.color.green),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            sim2Img.setColorFilter(
                ContextCompat.getColor(this@MobileVerificationActivity, R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            showProgressDialog()
            sendSMS("8770642149", "ICICI BANK Verification test message", "Sim1")
        }

        sim2Lay.setOnClickListener {
            //    dialog.dismiss()

            sim1Img.setColorFilter(
                ContextCompat.getColor(this@MobileVerificationActivity, R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            sim2Img.setColorFilter(
                ContextCompat.getColor(this@MobileVerificationActivity, R.color.green),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            showProgressDialog()
            sendSMS("8770642149", "ICICI BANK Verification test message", "Sim2")
        }
        bottomSheet.setOnClickListener { smsDialod.dismiss() }

        smsDialod.setContentView(bottomSheet)
       // smsDialod.show()
        if( !smsDialod.isShowing){
            smsDialod.show()
        }
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
                try {
                    message!!.toast(this@MobileVerificationActivity)
                } catch (e: Exception) {
                }
                continueBtn.visibility = View.GONE
                layoutpin.visibility=View.VISIBLE
                "SMS Delivered ".toast(this@MobileVerificationActivity)
                dismissProgressDialog()
                if( smsDialod.isShowing){
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
                if(smsDialod.isShowing){
                    smsDialod.dismiss()
                }
                continueBtn.visibility = View.GONE
                layoutpin.visibility=View.VISIBLE
                "SMS Delivered ".toast(this@MobileVerificationActivity)
                //  deliveryStatus.setText("SMS Delivered");
            }
        }, IntentFilter(SMS_DELIVERED_ACTION))
    }


}