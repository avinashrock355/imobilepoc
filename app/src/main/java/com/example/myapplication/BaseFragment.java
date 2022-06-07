package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dialog.DialogProgress;


public abstract class BaseFragment extends ASFragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e(this.getClass().getSimpleName(), "Fragment onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.e(this.getClass().getSimpleName(), "Fragment onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(this.getClass().getSimpleName(), "Fragment onStart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(this.getClass().getSimpleName(), "Fragment onResume");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "Fragment onActivityCreated");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "Fragment onViewCreated");
    }

    @Override
    public void onPause() {
        Log.d(this.getClass().getSimpleName(), "Fragment onPause");
        super.onPause();
    }

    @Override
    public View getView() {
        Log.d(this.getClass().getSimpleName(), "Fragment getView");
        return super.getView();

    }

    @Override
    public void onStop() {
        Log.d(this.getClass().getSimpleName(), "Fragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(this.getClass().getSimpleName(), "Fragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(this.getClass().getSimpleName(), "Fragment onDestroy");
        super.
                onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(this.getClass().getSimpleName(), "Fragment onDetach");
        super.onDetach();

    }

    public void startActivityWithAnimations(Intent intent, int enterAnim, int exitAnim) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(enterAnim, exitAnim);
    }

    public void startActivityForResultWithAnimations(Intent intent, int requestCode, int enterAnim, int exitAnim) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(enterAnim, exitAnim);
    }

    protected void setTitle(@StringRes int stringRes) {
        setTitle(getString(stringRes));
    }

    protected void setTitle(String title) {
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity a = (AppCompatActivity) getActivity();
            if (a.getSupportActionBar() != null) {
                a.getSupportActionBar().setTitle(title);
            }
        }
    }

    public void hideKeyBoard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showProgress() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            DialogProgress.show(getActivity());
        }
    }

    public void hideProgress() {
        if (getActivity() != null && !getActivity().isFinishing())
            DialogProgress.hide(getActivity());
    }


    public void showProgressCustomText(Bundle bundle) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (bundle == null)
                DialogProgress.showDialogWithCustomText(getActivity(), null);
            else
                DialogProgress.showDialogWithCustomText(getActivity(), bundle);
        }
    }
}