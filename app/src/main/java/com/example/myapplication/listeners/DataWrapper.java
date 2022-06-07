package com.example.myapplication.listeners;

public class DataWrapper {
    private int errorCode;
    private Exception apiException;
    private String data;

    public DataWrapper(int errorCode, Exception apiException, String data) {
        this.errorCode = errorCode;
        this.apiException = apiException;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Exception getApiException() {
        return apiException;
    }

    public String getData() {
        return data;
    }
}
