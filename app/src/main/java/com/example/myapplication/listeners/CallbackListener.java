package com.example.myapplication.listeners;

public interface CallbackListener {
    enum ShowwhatDialog {
        FORCE_UPDATE,
        MAINTAINENCE_MODE,
        OPTIONAL

    }

    void showDialog(ShowwhatDialog showwhatDialog, String message);
}
