import 'package:flutter_module/api/api_wrapper.dart';
import 'package:flutter_module/api/rest_services.dart';

import '../models/balance_request.dart';
import '../models/login_requesrt.dart';

class Repository {
  final RestService _restService;

  Repository(this._restService);

  Future<ApiWrapper> postLogin(LoginRequest mLoginRequest) =>
      _restService.postLogin(mLoginRequest);


  Future<ApiWrapper> getBalance(BalanceRequest mLoginRequest) =>
      _restService.getBalance(mLoginRequest);



}
