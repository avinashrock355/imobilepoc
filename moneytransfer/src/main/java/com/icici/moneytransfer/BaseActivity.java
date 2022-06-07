package com.icici.moneytransfer;

import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.icici.moneytransfer.dialog.DialogProgress;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class BaseActivity extends ASActivity {
    public static final String SMS_SENT_ACTION = "com.icici.mobileapp.SMS_SENT_ACTION";
    public static final String SMS_DELIVERED_ACTION = "com.icici.mobileapp.SMS_DELIVERED_ACTION";
    protected String mTitle;
    protected Toolbar mToolbar;
    protected AppBarLayout appBar;
    protected AppCompatTextView txt_toolbar;
    public ArrayList<String> titleList = new ArrayList<>();

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setCancelable(false);
      /*  AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(this);
        avLoadingIndicatorView.setIndicator("BallSpinFadeLoaderIndicator");
        avLoadingIndicatorView.setIndicatorColor(R.color.colorPrimary);
        mProgressDialog.setContentView(avLoadingIndicatorView);*/
        mProgressDialog.setIndeterminate(true);


        checkRunTimePermission();
        //requestHint();

    }


    public void setTitle(String title) {
        this.mTitle = title;
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    public void setToolbarTitle(String title) {
        if (txt_toolbar != null) {
            txt_toolbar.setText(title);
            titleList.add(title);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void init(Toolbar mToolbar, AppBarLayout appBar, AppCompatTextView txt_toolbar) {
        this.mToolbar = mToolbar;
        this.appBar = appBar;
        this.txt_toolbar = txt_toolbar;
        if (this.mToolbar != null) {
            setSupportActionBar(this.mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    public void showBackArrow() {
        if (mToolbar != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
    }

    public void setToolbarTheme(boolean dark) {
        if (dark) {
            mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            txt_toolbar.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            txt_toolbar.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
    }

    public Toolbar getTopToolbar() {
        return mToolbar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void startActivityWithAnimations(Intent intent, int enterAnim, int exitAnim) {
        super.startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }

    public void showProgress() {
        DialogProgress.show(this);
    }

    public void showProgressCustomText(Bundle bundle) {
        DialogProgress.showDialogWithCustomText(this, bundle);
    }

    public void hideProgress() {
        DialogProgress.hide(this);
    }

    public void showProgressDialog() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    //  private GoogleApiClient mCredentialsApiClient;

    // Construct a request for phone numbers and show the picker

    //---sends an SMS message to another device---

    public void sendSMS(String phoneNumber, String smsMessage,String isSim1) {


        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
                SMS_SENT_ACTION), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SMS_DELIVERED_ACTION), 0);
         //  SmsManager sms = SmsManager.getDefault();


            // sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);

            SubscriptionManager localSubscriptionManager = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                localSubscriptionManager = SubscriptionManager.from(this);

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                if (localSubscriptionManager != null && localSubscriptionManager.getActiveSubscriptionInfoCount() > 1) {
                    List localList = localSubscriptionManager.getActiveSubscriptionInfoList();

                    SubscriptionInfo simInfo1 = (SubscriptionInfo) localList.get(0);
                    SubscriptionInfo simInfo2 = (SubscriptionInfo) localList.get(1);

                    //SendSMS From SIM One
                    if(isSim1.equals("Sim1")){
                        SmsManager.getSmsManagerForSubscriptionId(
                                simInfo1.getSubscriptionId()).
                                sendTextMessage(phoneNumber, null, smsMessage+" sim1", sentPI, deliveredPI);

                    }else{

                        //SendSMS From SIM Two
                        SmsManager.getSmsManagerForSubscriptionId
                                (simInfo2.getSubscriptionId()).
                                sendTextMessage(phoneNumber, null, smsMessage+" sim2", sentPI, deliveredPI);

                    }
                    }
            }

      //  }
    }
    public void checkRunTimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED||ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED ) {

                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.SEND_SMS},
                        10);
            }
        }
    }
}