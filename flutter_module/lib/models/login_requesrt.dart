/// username : "558222293"
/// password : "ZVFjcDgwZWNWT2I3bnNZdkRhd29Ndz09"
/// loginEventTime : "Y1VlU3FLcUpOUDQ5UzZWMmJFanM3c0ZzZU0vbU9oQ0dTNGhabDBCczFxRT0="
/// grant_type : "password"
/// bank_id : "ICI"
/// language_id : "001"
/// device_type : "8"
/// device_id : "GQdVQsrTd/jGMleDWxSSEiYlClzdROn4V3WnoIgq5yojI/KuQ5T6fUtqh636V9HV"
/// machine_fingerprint : "eyJWRVJTSU9OIjoiMi4xLjIiLCJNRlAiOnsiQnJvd3NlciI6eyJVc2VyQWdlbnQiOiJNb3ppbGxhLzUuMCAoV2luZG93cyBOVCAxMC4wOyBXaW42NDsgeDY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvOTguMC40NzU4LjEwMiBTYWZhcmkvNTM3LjM2IEVkZy85OC4wLjExMDguNjIiLCJWZW5kb3IiOiJHb29nbGUgSW5jLiIsIlZlbmRvclN1YklEIjoiIiwiQnVpbGRJRCI6IjIwMDMwMTA3IiwiQ29va2llRW5hYmxlZCI6dHJ1ZX0sIklFUGx1Z2lucyI6e30sIk5ldHNjYXBlUGx1Z2lucyI6eyJQREYgVmlld2VyIjoiIiwiQ2hyb21lIFBERiBWaWV3ZXIiOiIiLCJDaHJvbWl1bSBQREYgVmlld2VyIjoiIiwiTWljcm9zb2Z0IEVkZ2UgUERGIFZpZXdlciI6IiIsIldlYktpdCBidWlsdC1pbiBQREYiOiIifSwiU2NyZWVuIjp7IkZ1bGxIZWlnaHQiOjcyMCwiQXZsSGVpZ2h0Ijo2ODAsIkZ1bGxXaWR0aCI6MTI4MCwiQXZsV2lkdGgiOjEyODAsIkNvbG9yRGVwdGgiOjI0LCJQaXhlbERlcHRoIjoyNH0sIlN5c3RlbSI6eyJQbGF0Zm9ybSI6IldpbjMyIiwic3lzdGVtTGFuZ3VhZ2UiOiJlbi1VUyIsIlRpbWV6b25lIjotMzMwfX0sIkV4dGVybmFsSVAiOiIiLCJNRVNDIjp7Im1lc2MiOiJtaT0yO2NkPTE1MDtpZD00NTttZXNjPTExMjI2OTU7bWVzYz0xMjEwODMwIn19"
/// client_id : ""
/// channel_id : ""
/// client_secret : ""
/// custom_data : null

class LoginRequest {
  LoginRequest({
      String username,
      String password,
      String loginEventTime,
      String grantType,
      String bankId,
      String languageId,
      String deviceType,
      String deviceId,
      String machineFingerprint,
      String clientId,
      String channelId,
      String clientSecret,
      dynamic customData,}){
    _username = username;
    _password = password;
    _loginEventTime = loginEventTime;
    _grantType = grantType;
    _bankId = bankId;
    _languageId = languageId;
    _deviceType = deviceType;
    _deviceId = deviceId;
    _machineFingerprint = machineFingerprint;
    _clientId = clientId;
    _channelId = channelId;
    _clientSecret = clientSecret;
    _customData = customData;
}

  LoginRequest.fromJson(dynamic json) {
    _username = json['username'];
    _password = json['password'];
    _loginEventTime = json['loginEventTime'];
    _grantType = json['grant_type'];
    _bankId = json['bank_id'];
    _languageId = json['language_id'];
    _deviceType = json['device_type'];
    _deviceId = json['device_id'];
    _machineFingerprint = json['machine_fingerprint'];
    _clientId = json['client_id'];
    _channelId = json['channel_id'];
    _clientSecret = json['client_secret'];
    _customData = json['custom_data'];
  }
  String _username;
  String _password;
  String _loginEventTime;
  String _grantType;
  String _bankId;
  String _languageId;
  String _deviceType;
  String _deviceId;
  String _machineFingerprint;
  String _clientId;
  String _channelId;
  String _clientSecret;
  dynamic _customData;

  String get username => _username;
  String get password => _password;
  String get loginEventTime => _loginEventTime;
  String get grantType => _grantType;
  String get bankId => _bankId;
  String get languageId => _languageId;
  String get deviceType => _deviceType;
  String get deviceId => _deviceId;
  String get machineFingerprint => _machineFingerprint;
  String get clientId => _clientId;
  String get channelId => _channelId;
  String get clientSecret => _clientSecret;
  dynamic get customData => _customData;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['username'] = _username;
    map['password'] = _password;
    map['loginEventTime'] = _loginEventTime;
    map['grant_type'] = _grantType;
    map['bank_id'] = _bankId;
    map['language_id'] = _languageId;
    map['device_type'] = _deviceType;
    map['device_id'] = _deviceId;
    map['machine_fingerprint'] = _machineFingerprint;
    map['client_id'] = _clientId;
    map['channel_id'] = _channelId;
    map['client_secret'] = _clientSecret;
    map['custom_data'] = _customData;
    return map;
  }

}