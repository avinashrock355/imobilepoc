package com.icici.moneytransfer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ASActivity extends AppCompatActivity {

    protected final String LOG_TAG = this.getClass().getSimpleName();
    protected final String LIFE_TAG = "[LIFECYCLE] " + LOG_TAG;
    protected boolean isFirstLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        /*if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }*/
        isFirstLaunch = (savedInstanceState == null);
        Log.v(LIFE_TAG, isFirstLaunch ? "onCreate [first-launch]" : "onCreate [restarted]");
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.v(LIFE_TAG, "onAttachFragment: " + fragment.getClass().getSimpleName());
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.v(LIFE_TAG, "onContentChanged");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(LIFE_TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(LIFE_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(LIFE_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(LIFE_TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(LIFE_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(LIFE_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LIFE_TAG, "onDestroy");
    }
}
