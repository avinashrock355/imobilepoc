package com.example.myapplication.domain.model

import java.lang.Exception

sealed class Result{
    data class Success<T>(val data : T,var forApi:String="") : Result()
    data class Failure(val msg : String) : Result()
    data class Error<T>(val exception : T) : Result()
}
