import 'dart:convert';

import 'package:flutter_module/api/api_error.dart';
import 'package:flutter_module/api/api_wrapper.dart';
import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';

import '../models/balance_model.dart';
import '../models/balance_request.dart';
import '../models/login_requesrt.dart';
import '../models/login_response.dart';
import '../utils/aes_helper.dart';
import '../utils/sharedpreference_helper/sharepreference_helper.dart';

class RestService {
  static const String _baseUrl = "https://ribuiuat.icicibank.com/";
  static const String encKey = "7061737323313233";
  static const String bankID = "ICI";
  static const String currency = "INR";

  static const String _login = "loginservice";
  static const String _netBalance = "dashboardAPI/netBalance";
  Dio _dio;

  RestService() {
    _dio = Dio();
    _dio.options
      ..baseUrl = _baseUrl
      ..headers = {"Content-Type": "application/json", "IV": AESHelper.IValue};
    /*if (SharePreferencesHelper.getBool(SharePreferencesHelper.ISLOGIN) !=
            null &&
        SharePreferencesHelper.getBool(SharePreferencesHelper.ISLOGIN)) {*/
    /*  var list = SharePreferencesHelper.getStringList(
          SharePreferencesHelper.COKKIELIST);

print("----sfx to,k  ${list[3]}");
      _dio.options.headers = {
        "Content-Type": "application/json",
        "XSRF-TOKEN": list[3],
        "Cookie":"${list[0]} ${list[1]} ${list[2]} device_id=7",

      };*/
    //}
  }

  Future<ApiWrapper> postLogin(LoginRequest mLoginRequest) async {
    debugPrint("======   ${mLoginRequest.toJson().toString()}");
    var response = await _dio.post(_login, data: mLoginRequest.toJson()).whenComplete(() {}).catchError((error){
      debugPrint("error======   ${error.toString()}");
    });
    debugPrint("======◢◤◢◤   $response");
    if (response.statusCode == 200) {
      var list = response.headers.map["Set-Cookie"];
      var ll = list.map((e) => {e.split(" ")});
      List<String> list1 = [];
      for (var element in ll) {
        element.first[0];
        list1.add(element.first[0]);
      }
      // var s = json.encode(list1);
      SharePreferencesHelper.setStringList(
          SharePreferencesHelper.COKKIELIST, list1);
      debugPrint("ef-------${list1.toString()}");
      var loginRes = LoginResponse.fromJson(response.data);
      return ApiWrapper(loginRes, response.statusCode, null);
    } else {
      return ApiWrapper(null, response.statusCode,
          ApiError.name(response.statusCode, response.statusMessage, null));
    }
  }

  Future<ApiWrapper> getBalance(BalanceRequest mLoginRequest) async {
    var list = SharePreferencesHelper.getStringList(
        SharePreferencesHelper.COKKIELIST);

    print("----sfx to,k  ${list[3]}");
    _dio.options.headers = {
      "Content-Type": "application/json",
      "XSRF-TOKEN": list[3],
      "Cookie":"${list[0]} ${list[1]} ${list[2]} device_id=7",

    };
    debugPrint("======   ${mLoginRequest.toJson().toString()}");
    var response = await _dio.post(_netBalance, data: mLoginRequest.toJson()).whenComplete(() {}).catchError((error){
      debugPrint("error======   ${error.toString()}");
    });
    debugPrint("======◢◤◢◤   $response");
    if (response.statusCode == 200) {
      var loginRes = BalanceModel.fromJson(response.data);
      return ApiWrapper(loginRes, response.statusCode, null);
    } else {
      return ApiWrapper(null, response.statusCode,
          ApiError.name(response.statusCode, response.statusMessage, null));
    }
  }
}
