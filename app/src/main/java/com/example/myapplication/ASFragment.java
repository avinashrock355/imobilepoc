package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ASFragment extends Fragment {

    protected final String LOG_TAG = this.getClass().getSimpleName();
    protected final String LIFE_TAG = "[LIFECYCLE] " + LOG_TAG;
    protected boolean isFirstLaunch = true;

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        isFirstLaunch = (savedInstanceState == null);
        Log.v(LIFE_TAG, isFirstLaunch ? "onInflate [first-launch]" : "onInflate [restarted]");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(LIFE_TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstLaunch = (savedInstanceState == null);
        Log.v(LIFE_TAG, isFirstLaunch ? "onCreate [first-launch]" : "onCreate [restarted]");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(LIFE_TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v(LIFE_TAG, "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(LIFE_TAG, "onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.v(LIFE_TAG, "onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(LIFE_TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(LIFE_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(LIFE_TAG, "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(LIFE_TAG, "onSaveInstanceState");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(LIFE_TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(LIFE_TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LIFE_TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v(LIFE_TAG, "onDetach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.v(LIFE_TAG, "onHidden " + (hidden ? "[VISIBLE]" : "[NOT VISIBLE]"));
    }

}
