import 'dart:convert';

/// token_type : "bearer"
/// expires_in : "7200"
/// user : {"userId":"558222302","userType":"1","corporateId":"558222302","isTermsandConditionsAcceptanceRequired":"N","forceSignonPasswordChangeFlag":"N","forceTxnPasswordChangeFlag":"N","forceSecurityQuestionChangeFlag":"N","isUnifiedLoginAllowed":"N","uniqueId":"b5ff86ac8be3518de1c01dd2afed7da81c386ff1","forceUSSDPinChangeFlag":"N","forceModeChangeFlag":"N"}
/// userProfile : {"userProfiledetails":{"userProfileSess":null,"bankId":"ICI","userId":"558222302","orgId":"558222302","principalId":"558222302","userType":{"codeType":"URT","cmCode":"1","codeDescription":"Consumer User"},"customerId":"M3/558222302","salutation":{"codeType":"DSAL","cmCode":"MR.","codeDescription":"Mr."},"userNickName":"One","firstName":"Retail","middleName":null,"lastName":"One","primaryBranchId":null,"primaryDivisionId":null,"divisionAccessIndicator":{"codeType":"GLO","cmCode":"G","codeDescription":"Global"},"primaryAccountId":null,"lastTransactionDate":"2022-01-20T14:53:40","loginDate":"2022-03-10T10:56:08","logoffDate":"2022-03-10T11:05:07","limitScheme":{"codeType":"LSH","cmCode":"DRL","codeDescription":"Default Retail Limit DRL"},"rangeLimitScheme":{"codeType":"LSH","cmCode":"DRL","codeDescription":"Default Retail Limit DRL"},"loginAllowed":{"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"},"transactionAllowed":{"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"},"language":{"codeType":"LANG","cmCode":"001","codeDescription":"English"},"accountFormat":{"codeType":"ACFT","cmCode":"NNCA","codeDescription":"NickName(Currency)-AccountId"},"segmentName":{"codeType":"SEG","cmCode":"ICICIRET","codeDescription":"ICICIRET"},"accessScheme":{"codeType":"AMS","cmCode":"ICICIRET","codeDescription":"ICICIRET"},"transactionAuthorizationScheme":{"codeType":"ASC","cmCode":"RDF","codeDescription":"ICICI Retail Default"},"loginChannel":{"codeType":"AIS","cmCode":"I","codeDescription":"Internet"},"lastUnsuccessfullLoginChannel":{"codeType":"AIS","cmCode":"I","codeDescription":"Internet"},"dateFormat":{"codeType":"DFT","cmCode":"17","codeDescription":"dd-MM-yyyy"},"calendarType":{"codeType":"CALT","cmCode":"GR","codeDescription":"Gregorian"},"unifiedLoginUser":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"lastSuccessfullLoginTime":null,"lastUnsuccessfullLoginTime":"2022-02-15T18:04:20","email":"retail1@gmail.com","confidentialTransactionAccess":{"codeType":"CTE","cmCode":"N","codeDescription":"No Access"},"prospectIndicator":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"amountFormat":{"codeType":"AFT","cmCode":"0","codeDescription":"Lakh Format"},"outOfOfficePreference":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"accountMaskingRequired":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"preferredTimeZone":null,"userRoleId":null,"userRoleName":null,"mobileNumber":"9797979797","phoneNumber":null,"status":null,"multiCurrencyTxnAllowed":{"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"},"categoryCode":{"codeType":"CAT","cmCode":"SOF","codeDescription":"SOFTWARE"}},"fedId":"3006562671","commonDetails":"SYNC_REQUIRED=N|BRAND_ID=GB0GB|IS_OFFLINE_FETCH=N|","deviceId":"uf0lWBcLVsToWImx2AdUtYm1CcRWLmI1DiBlz2nHVCgH9n5Skz9TOZyXsDWvhaFQ","widgetVisibility":"bankAccountDebitCard=Y|dashboardTotalSum=Y|dashboardCreditCard=Y"}
/// menuProfile : [{"menuId":"OREMS","menuText":"Emergency Services","parentMenuId":"!","menuIdType":"PARENT","menuIdOrder":"1"},{"menuId":"ORDSB","menuText":"Dashboard","parentMenuId":"!","menuIdType":"PARENT","menuIdOrder":"1"},{"menuId":"ORCNT","menuText":"Contacts","parentMenuId":"!","menuIdType":"PARENT","menuIdOrder":"1"},{"menuId":"ORBIL","menuText":"Bills","parentMenuId":"!","menuIdType":"PARENT","menuIdOrder":"1"},{"menuId":"ORATM","menuText":"ATMs","parentMenuId":"!","menuIdType":"PARENT","menuIdOrder":"1"},{"menuId":"BBPBLP","menuText":"BBPS API","parentMenuId":"!","menuIdType":"PARENT","menuIdOrder":"1"},{"menuId":"ORACC","menuText":"Accounts","parentMenuId":"ORDSB","menuIdType":"CHILD","menuIdOrder":"1"},{"menuId":"ORCRD","menuText":"Cards","parentMenuId":"ORDSB","menuIdType":"CHILD","menuIdOrder":"2"},{"menuId":"ORLNS","menuText":"Loans","parentMenuId":"ORDSB","menuIdType":"CHILD","menuIdOrder":"3"},{"menuId":"ORINV","menuText":"Investments","parentMenuId":"ORDSB","menuIdType":"CHILD","menuIdOrder":"4"}]
/// sessionId : "02941389-4915-4664-a630-bca33d9c060c"
/// xsrfToken : "7594447d-de9e-4690-bfed-269ea62cac83"

String loginresToJson(LoginResponse data) => json.encode(data.toJson());
class LoginResponse {
  LoginResponse({
      String tokenType, 
      String expiresIn, 
      User user, 
      UserProfile userProfile, 
      List<MenuProfile> menuProfile, 
      String sessionId, 
      String xsrfToken,}){
    _tokenType = tokenType;
    _expiresIn = expiresIn;
    _user = user;
    _userProfile = userProfile;
    _menuProfile = menuProfile;
    _sessionId = sessionId;
    _xsrfToken = xsrfToken;
}

  LoginResponse.fromJson(dynamic json) {
    _tokenType = json['token_type'];
    _expiresIn = json['expires_in'];
    _user = json['user'] != null ? User.fromJson(json['user']) : null;
    _userProfile = json['userProfile'] != null ? UserProfile.fromJson(json['userProfile']) : null;
    if (json['menuProfile'] != null) {
      _menuProfile = [];
      json['menuProfile'].forEach((v) {
        _menuProfile.add(MenuProfile.fromJson(v));
      });
    }
    _sessionId = json['sessionId'];
    _xsrfToken = json['xsrfToken'];
  }
  String _tokenType;
  String _expiresIn;
  User _user;
  UserProfile _userProfile;
  List<MenuProfile> _menuProfile;
  String _sessionId;
  String _xsrfToken;

  String get tokenType => _tokenType;
  String get expiresIn => _expiresIn;
  User get user => _user;
  UserProfile get userProfile => _userProfile;
  List<MenuProfile> get menuProfile => _menuProfile;
  String get sessionId => _sessionId;
  String get xsrfToken => _xsrfToken;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['token_type'] = _tokenType;
    map['expires_in'] = _expiresIn;
    if (_user != null) {
      map['user'] = _user.toJson();
    }
    if (_userProfile != null) {
      map['userProfile'] = _userProfile.toJson();
    }
    if (_menuProfile != null) {
      map['menuProfile'] = _menuProfile.map((v) => v.toJson()).toList();
    }
    map['sessionId'] = _sessionId;
    map['xsrfToken'] = _xsrfToken;
    return map;
  }

}

/// menuId : "OREMS"
/// menuText : "Emergency Services"
/// parentMenuId : "!"
/// menuIdType : "PARENT"
/// menuIdOrder : "1"

class MenuProfile {
  MenuProfile({
      String menuId, 
      String menuText, 
      String parentMenuId, 
      String menuIdType, 
      String menuIdOrder,}){
    _menuId = menuId;
    _menuText = menuText;
    _parentMenuId = parentMenuId;
    _menuIdType = menuIdType;
    _menuIdOrder = menuIdOrder;
}

  MenuProfile.fromJson(dynamic json) {
    _menuId = json['menuId'];
    _menuText = json['menuText'];
    _parentMenuId = json['parentMenuId'];
    _menuIdType = json['menuIdType'];
    _menuIdOrder = json['menuIdOrder'];
  }
  String _menuId;
  String _menuText;
  String _parentMenuId;
  String _menuIdType;
  String _menuIdOrder;

  String get menuId => _menuId;
  String get menuText => _menuText;
  String get parentMenuId => _parentMenuId;
  String get menuIdType => _menuIdType;
  String get menuIdOrder => _menuIdOrder;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['menuId'] = _menuId;
    map['menuText'] = _menuText;
    map['parentMenuId'] = _parentMenuId;
    map['menuIdType'] = _menuIdType;
    map['menuIdOrder'] = _menuIdOrder;
    return map;
  }

}

/// userProfiledetails : {"userProfileSess":null,"bankId":"ICI","userId":"558222302","orgId":"558222302","principalId":"558222302","userType":{"codeType":"URT","cmCode":"1","codeDescription":"Consumer User"},"customerId":"M3/558222302","salutation":{"codeType":"DSAL","cmCode":"MR.","codeDescription":"Mr."},"userNickName":"One","firstName":"Retail","middleName":null,"lastName":"One","primaryBranchId":null,"primaryDivisionId":null,"divisionAccessIndicator":{"codeType":"GLO","cmCode":"G","codeDescription":"Global"},"primaryAccountId":null,"lastTransactionDate":"2022-01-20T14:53:40","loginDate":"2022-03-10T10:56:08","logoffDate":"2022-03-10T11:05:07","limitScheme":{"codeType":"LSH","cmCode":"DRL","codeDescription":"Default Retail Limit DRL"},"rangeLimitScheme":{"codeType":"LSH","cmCode":"DRL","codeDescription":"Default Retail Limit DRL"},"loginAllowed":{"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"},"transactionAllowed":{"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"},"language":{"codeType":"LANG","cmCode":"001","codeDescription":"English"},"accountFormat":{"codeType":"ACFT","cmCode":"NNCA","codeDescription":"NickName(Currency)-AccountId"},"segmentName":{"codeType":"SEG","cmCode":"ICICIRET","codeDescription":"ICICIRET"},"accessScheme":{"codeType":"AMS","cmCode":"ICICIRET","codeDescription":"ICICIRET"},"transactionAuthorizationScheme":{"codeType":"ASC","cmCode":"RDF","codeDescription":"ICICI Retail Default"},"loginChannel":{"codeType":"AIS","cmCode":"I","codeDescription":"Internet"},"lastUnsuccessfullLoginChannel":{"codeType":"AIS","cmCode":"I","codeDescription":"Internet"},"dateFormat":{"codeType":"DFT","cmCode":"17","codeDescription":"dd-MM-yyyy"},"calendarType":{"codeType":"CALT","cmCode":"GR","codeDescription":"Gregorian"},"unifiedLoginUser":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"lastSuccessfullLoginTime":null,"lastUnsuccessfullLoginTime":"2022-02-15T18:04:20","email":"retail1@gmail.com","confidentialTransactionAccess":{"codeType":"CTE","cmCode":"N","codeDescription":"No Access"},"prospectIndicator":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"amountFormat":{"codeType":"AFT","cmCode":"0","codeDescription":"Lakh Format"},"outOfOfficePreference":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"accountMaskingRequired":{"codeType":"YNFG","cmCode":"N","codeDescription":"No"},"preferredTimeZone":null,"userRoleId":null,"userRoleName":null,"mobileNumber":"9797979797","phoneNumber":null,"status":null,"multiCurrencyTxnAllowed":{"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"},"categoryCode":{"codeType":"CAT","cmCode":"SOF","codeDescription":"SOFTWARE"}}
/// fedId : "3006562671"
/// commonDetails : "SYNC_REQUIRED=N|BRAND_ID=GB0GB|IS_OFFLINE_FETCH=N|"
/// deviceId : "uf0lWBcLVsToWImx2AdUtYm1CcRWLmI1DiBlz2nHVCgH9n5Skz9TOZyXsDWvhaFQ"
/// widgetVisibility : "bankAccountDebitCard=Y|dashboardTotalSum=Y|dashboardCreditCard=Y"

class UserProfile {
  UserProfile({
      UserProfiledetails userProfiledetails, 
      String fedId, 
      String commonDetails, 
      String deviceId, 
      String widgetVisibility,}){
    _userProfiledetails = userProfiledetails;
    _fedId = fedId;
    _commonDetails = commonDetails;
    _deviceId = deviceId;
    _widgetVisibility = widgetVisibility;
}

  UserProfile.fromJson(dynamic json) {
    _userProfiledetails = json['userProfiledetails'] != null ? UserProfiledetails.fromJson(json['userProfiledetails']) : null;
    _fedId = json['fedId'];
    _commonDetails = json['commonDetails'];
    _deviceId = json['deviceId'];
    _widgetVisibility = json['widgetVisibility'];
  }
  UserProfiledetails _userProfiledetails;
  String _fedId;
  String _commonDetails;
  String _deviceId;
  String _widgetVisibility;

  UserProfiledetails get userProfiledetails => _userProfiledetails;
  String get fedId => _fedId;
  String get commonDetails => _commonDetails;
  String get deviceId => _deviceId;
  String get widgetVisibility => _widgetVisibility;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    if (_userProfiledetails != null) {
      map['userProfiledetails'] = _userProfiledetails.toJson();
    }
    map['fedId'] = _fedId;
    map['commonDetails'] = _commonDetails;
    map['deviceId'] = _deviceId;
    map['widgetVisibility'] = _widgetVisibility;
    return map;
  }

}

/// userProfileSess : null
/// bankId : "ICI"
/// userId : "558222302"
/// orgId : "558222302"
/// principalId : "558222302"
/// userType : {"codeType":"URT","cmCode":"1","codeDescription":"Consumer User"}
/// customerId : "M3/558222302"
/// salutation : {"codeType":"DSAL","cmCode":"MR.","codeDescription":"Mr."}
/// userNickName : "One"
/// firstName : "Retail"
/// middleName : null
/// lastName : "One"
/// primaryBranchId : null
/// primaryDivisionId : null
/// divisionAccessIndicator : {"codeType":"GLO","cmCode":"G","codeDescription":"Global"}
/// primaryAccountId : null
/// lastTransactionDate : "2022-01-20T14:53:40"
/// loginDate : "2022-03-10T10:56:08"
/// logoffDate : "2022-03-10T11:05:07"
/// limitScheme : {"codeType":"LSH","cmCode":"DRL","codeDescription":"Default Retail Limit DRL"}
/// rangeLimitScheme : {"codeType":"LSH","cmCode":"DRL","codeDescription":"Default Retail Limit DRL"}
/// loginAllowed : {"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"}
/// transactionAllowed : {"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"}
/// language : {"codeType":"LANG","cmCode":"001","codeDescription":"English"}
/// accountFormat : {"codeType":"ACFT","cmCode":"NNCA","codeDescription":"NickName(Currency)-AccountId"}
/// segmentName : {"codeType":"SEG","cmCode":"ICICIRET","codeDescription":"ICICIRET"}
/// accessScheme : {"codeType":"AMS","cmCode":"ICICIRET","codeDescription":"ICICIRET"}
/// transactionAuthorizationScheme : {"codeType":"ASC","cmCode":"RDF","codeDescription":"ICICI Retail Default"}
/// loginChannel : {"codeType":"AIS","cmCode":"I","codeDescription":"Internet"}
/// lastUnsuccessfullLoginChannel : {"codeType":"AIS","cmCode":"I","codeDescription":"Internet"}
/// dateFormat : {"codeType":"DFT","cmCode":"17","codeDescription":"dd-MM-yyyy"}
/// calendarType : {"codeType":"CALT","cmCode":"GR","codeDescription":"Gregorian"}
/// unifiedLoginUser : {"codeType":"YNFG","cmCode":"N","codeDescription":"No"}
/// lastSuccessfullLoginTime : null
/// lastUnsuccessfullLoginTime : "2022-02-15T18:04:20"
/// email : "retail1@gmail.com"
/// confidentialTransactionAccess : {"codeType":"CTE","cmCode":"N","codeDescription":"No Access"}
/// prospectIndicator : {"codeType":"YNFG","cmCode":"N","codeDescription":"No"}
/// amountFormat : {"codeType":"AFT","cmCode":"0","codeDescription":"Lakh Format"}
/// outOfOfficePreference : {"codeType":"YNFG","cmCode":"N","codeDescription":"No"}
/// accountMaskingRequired : {"codeType":"YNFG","cmCode":"N","codeDescription":"No"}
/// preferredTimeZone : null
/// userRoleId : null
/// userRoleName : null
/// mobileNumber : "9797979797"
/// phoneNumber : null
/// status : null
/// multiCurrencyTxnAllowed : {"codeType":"YNFG","cmCode":"Y","codeDescription":"Yes"}
/// categoryCode : {"codeType":"CAT","cmCode":"SOF","codeDescription":"SOFTWARE"}

class UserProfiledetails {
  UserProfiledetails({
      dynamic userProfileSess, 
      String bankId, 
      String userId, 
      String orgId, 
      String principalId, 
      UserType userType, 
      String customerId, 
      Salutation salutation, 
      String userNickName, 
      String firstName, 
      dynamic middleName, 
      String lastName, 
      dynamic primaryBranchId, 
      dynamic primaryDivisionId, 
      DivisionAccessIndicator divisionAccessIndicator, 
      dynamic primaryAccountId, 
      String lastTransactionDate, 
      String loginDate, 
      String logoffDate, 
      LimitScheme limitScheme, 
      RangeLimitScheme rangeLimitScheme, 
      LoginAllowed loginAllowed, 
      TransactionAllowed transactionAllowed, 
      Language language, 
      AccountFormat accountFormat, 
      SegmentName segmentName, 
      AccessScheme accessScheme, 
      TransactionAuthorizationScheme transactionAuthorizationScheme, 
      LoginChannel loginChannel, 
      LastUnsuccessfullLoginChannel lastUnsuccessfullLoginChannel, 
      DateFormat dateFormat, 
      CalendarType calendarType, 
      UnifiedLoginUser unifiedLoginUser, 
      dynamic lastSuccessfullLoginTime, 
      String lastUnsuccessfullLoginTime, 
      String email, 
      ConfidentialTransactionAccess confidentialTransactionAccess, 
      ProspectIndicator prospectIndicator, 
      AmountFormat amountFormat, 
      OutOfOfficePreference outOfOfficePreference, 
      AccountMaskingRequired accountMaskingRequired, 
      dynamic preferredTimeZone, 
      dynamic userRoleId, 
      dynamic userRoleName, 
      String mobileNumber, 
      dynamic phoneNumber, 
      dynamic status, 
      MultiCurrencyTxnAllowed multiCurrencyTxnAllowed, 
      CategoryCode categoryCode,}){
    _userProfileSess = userProfileSess;
    _bankId = bankId;
    _userId = userId;
    _orgId = orgId;
    _principalId = principalId;
    _userType = userType;
    _customerId = customerId;
    _salutation = salutation;
    _userNickName = userNickName;
    _firstName = firstName;
    _middleName = middleName;
    _lastName = lastName;
    _primaryBranchId = primaryBranchId;
    _primaryDivisionId = primaryDivisionId;
    _divisionAccessIndicator = divisionAccessIndicator;
    _primaryAccountId = primaryAccountId;
    _lastTransactionDate = lastTransactionDate;
    _loginDate = loginDate;
    _logoffDate = logoffDate;
    _limitScheme = limitScheme;
    _rangeLimitScheme = rangeLimitScheme;
    _loginAllowed = loginAllowed;
    _transactionAllowed = transactionAllowed;
    _language = language;
    _accountFormat = accountFormat;
    _segmentName = segmentName;
    _accessScheme = accessScheme;
    _transactionAuthorizationScheme = transactionAuthorizationScheme;
    _loginChannel = loginChannel;
    _lastUnsuccessfullLoginChannel = lastUnsuccessfullLoginChannel;
    _dateFormat = dateFormat;
    _calendarType = calendarType;
    _unifiedLoginUser = unifiedLoginUser;
    _lastSuccessfullLoginTime = lastSuccessfullLoginTime;
    _lastUnsuccessfullLoginTime = lastUnsuccessfullLoginTime;
    _email = email;
    _confidentialTransactionAccess = confidentialTransactionAccess;
    _prospectIndicator = prospectIndicator;
    _amountFormat = amountFormat;
    _outOfOfficePreference = outOfOfficePreference;
    _accountMaskingRequired = accountMaskingRequired;
    _preferredTimeZone = preferredTimeZone;
    _userRoleId = userRoleId;
    _userRoleName = userRoleName;
    _mobileNumber = mobileNumber;
    _phoneNumber = phoneNumber;
    _status = status;
    _multiCurrencyTxnAllowed = multiCurrencyTxnAllowed;
    _categoryCode = categoryCode;
}

  UserProfiledetails.fromJson(dynamic json) {
    _userProfileSess = json['userProfileSess'];
    _bankId = json['bankId'];
    _userId = json['userId'];
    _orgId = json['orgId'];
    _principalId = json['principalId'];
    _userType = json['userType'] != null ? UserType.fromJson(json['userType']) : null;
    _customerId = json['customerId'];
    _salutation = json['salutation'] != null ? Salutation.fromJson(json['salutation']) : null;
    _userNickName = json['userNickName'];
    _firstName = json['firstName'];
    _middleName = json['middleName'];
    _lastName = json['lastName'];
    _primaryBranchId = json['primaryBranchId'];
    _primaryDivisionId = json['primaryDivisionId'];
    _divisionAccessIndicator = json['divisionAccessIndicator'] != null ? DivisionAccessIndicator.fromJson(json['divisionAccessIndicator']) : null;
    _primaryAccountId = json['primaryAccountId'];
    _lastTransactionDate = json['lastTransactionDate'];
    _loginDate = json['loginDate'];
    _logoffDate = json['logoffDate'];
    _limitScheme = json['limitScheme'] != null ? LimitScheme.fromJson(json['limitScheme']) : null;
    _rangeLimitScheme = json['rangeLimitScheme'] != null ? RangeLimitScheme.fromJson(json['rangeLimitScheme']) : null;
    _loginAllowed = json['loginAllowed'] != null ? LoginAllowed.fromJson(json['loginAllowed']) : null;
    _transactionAllowed = json['transactionAllowed'] != null ? TransactionAllowed.fromJson(json['transactionAllowed']) : null;
    _language = json['language'] != null ? Language.fromJson(json['language']) : null;
    _accountFormat = json['accountFormat'] != null ? AccountFormat.fromJson(json['accountFormat']) : null;
    _segmentName = json['segmentName'] != null ? SegmentName.fromJson(json['segmentName']) : null;
    _accessScheme = json['accessScheme'] != null ? AccessScheme.fromJson(json['accessScheme']) : null;
    _transactionAuthorizationScheme = json['transactionAuthorizationScheme'] != null ? TransactionAuthorizationScheme.fromJson(json['transactionAuthorizationScheme']) : null;
    _loginChannel = json['loginChannel'] != null ? LoginChannel.fromJson(json['loginChannel']) : null;
    _lastUnsuccessfullLoginChannel = json['lastUnsuccessfullLoginChannel'] != null ? LastUnsuccessfullLoginChannel.fromJson(json['lastUnsuccessfullLoginChannel']) : null;
    _dateFormat = json['dateFormat'] != null ? DateFormat.fromJson(json['dateFormat']) : null;
    _calendarType = json['calendarType'] != null ? CalendarType.fromJson(json['calendarType']) : null;
    _unifiedLoginUser = json['unifiedLoginUser'] != null ? UnifiedLoginUser.fromJson(json['unifiedLoginUser']) : null;
    _lastSuccessfullLoginTime = json['lastSuccessfullLoginTime'];
    _lastUnsuccessfullLoginTime = json['lastUnsuccessfullLoginTime'];
    _email = json['email'];
    _confidentialTransactionAccess = json['confidentialTransactionAccess'] != null ? ConfidentialTransactionAccess.fromJson(json['confidentialTransactionAccess']) : null;
    _prospectIndicator = json['prospectIndicator'] != null ? ProspectIndicator.fromJson(json['prospectIndicator']) : null;
    _amountFormat = json['amountFormat'] != null ? AmountFormat.fromJson(json['amountFormat']) : null;
    _outOfOfficePreference = json['outOfOfficePreference'] != null ? OutOfOfficePreference.fromJson(json['outOfOfficePreference']) : null;
    _accountMaskingRequired = json['accountMaskingRequired'] != null ? AccountMaskingRequired.fromJson(json['accountMaskingRequired']) : null;
    _preferredTimeZone = json['preferredTimeZone'];
    _userRoleId = json['userRoleId'];
    _userRoleName = json['userRoleName'];
    _mobileNumber = json['mobileNumber'];
    _phoneNumber = json['phoneNumber'];
    _status = json['status'];
    _multiCurrencyTxnAllowed = json['multiCurrencyTxnAllowed'] != null ? MultiCurrencyTxnAllowed.fromJson(json['multiCurrencyTxnAllowed']) : null;
    _categoryCode = json['categoryCode'] != null ? CategoryCode.fromJson(json['categoryCode']) : null;
  }
  dynamic _userProfileSess;
  String _bankId;
  String _userId;
  String _orgId;
  String _principalId;
  UserType _userType;
  String _customerId;
  Salutation _salutation;
  String _userNickName;
  String _firstName;
  dynamic _middleName;
  String _lastName;
  dynamic _primaryBranchId;
  dynamic _primaryDivisionId;
  DivisionAccessIndicator _divisionAccessIndicator;
  dynamic _primaryAccountId;
  String _lastTransactionDate;
  String _loginDate;
  String _logoffDate;
  LimitScheme _limitScheme;
  RangeLimitScheme _rangeLimitScheme;
  LoginAllowed _loginAllowed;
  TransactionAllowed _transactionAllowed;
  Language _language;
  AccountFormat _accountFormat;
  SegmentName _segmentName;
  AccessScheme _accessScheme;
  TransactionAuthorizationScheme _transactionAuthorizationScheme;
  LoginChannel _loginChannel;
  LastUnsuccessfullLoginChannel _lastUnsuccessfullLoginChannel;
  DateFormat _dateFormat;
  CalendarType _calendarType;
  UnifiedLoginUser _unifiedLoginUser;
  dynamic _lastSuccessfullLoginTime;
  String _lastUnsuccessfullLoginTime;
  String _email;
  ConfidentialTransactionAccess _confidentialTransactionAccess;
  ProspectIndicator _prospectIndicator;
  AmountFormat _amountFormat;
  OutOfOfficePreference _outOfOfficePreference;
  AccountMaskingRequired _accountMaskingRequired;
  dynamic _preferredTimeZone;
  dynamic _userRoleId;
  dynamic _userRoleName;
  String _mobileNumber;
  dynamic _phoneNumber;
  dynamic _status;
  MultiCurrencyTxnAllowed _multiCurrencyTxnAllowed;
  CategoryCode _categoryCode;

  dynamic get userProfileSess => _userProfileSess;
  String get bankId => _bankId;
  String get userId => _userId;
  String get orgId => _orgId;
  String get principalId => _principalId;
  UserType get userType => _userType;
  String get customerId => _customerId;
  Salutation get salutation => _salutation;
  String get userNickName => _userNickName;
  String get firstName => _firstName;
  dynamic get middleName => _middleName;
  String get lastName => _lastName;
  dynamic get primaryBranchId => _primaryBranchId;
  dynamic get primaryDivisionId => _primaryDivisionId;
  DivisionAccessIndicator get divisionAccessIndicator => _divisionAccessIndicator;
  dynamic get primaryAccountId => _primaryAccountId;
  String get lastTransactionDate => _lastTransactionDate;
  String get loginDate => _loginDate;
  String get logoffDate => _logoffDate;
  LimitScheme get limitScheme => _limitScheme;
  RangeLimitScheme get rangeLimitScheme => _rangeLimitScheme;
  LoginAllowed get loginAllowed => _loginAllowed;
  TransactionAllowed get transactionAllowed => _transactionAllowed;
  Language get language => _language;
  AccountFormat get accountFormat => _accountFormat;
  SegmentName get segmentName => _segmentName;
  AccessScheme get accessScheme => _accessScheme;
  TransactionAuthorizationScheme get transactionAuthorizationScheme => _transactionAuthorizationScheme;
  LoginChannel get loginChannel => _loginChannel;
  LastUnsuccessfullLoginChannel get lastUnsuccessfullLoginChannel => _lastUnsuccessfullLoginChannel;
  DateFormat get dateFormat => _dateFormat;
  CalendarType get calendarType => _calendarType;
  UnifiedLoginUser get unifiedLoginUser => _unifiedLoginUser;
  dynamic get lastSuccessfullLoginTime => _lastSuccessfullLoginTime;
  String get lastUnsuccessfullLoginTime => _lastUnsuccessfullLoginTime;
  String get email => _email;
  ConfidentialTransactionAccess get confidentialTransactionAccess => _confidentialTransactionAccess;
  ProspectIndicator get prospectIndicator => _prospectIndicator;
  AmountFormat get amountFormat => _amountFormat;
  OutOfOfficePreference get outOfOfficePreference => _outOfOfficePreference;
  AccountMaskingRequired get accountMaskingRequired => _accountMaskingRequired;
  dynamic get preferredTimeZone => _preferredTimeZone;
  dynamic get userRoleId => _userRoleId;
  dynamic get userRoleName => _userRoleName;
  String get mobileNumber => _mobileNumber;
  dynamic get phoneNumber => _phoneNumber;
  dynamic get status => _status;
  MultiCurrencyTxnAllowed get multiCurrencyTxnAllowed => _multiCurrencyTxnAllowed;
  CategoryCode get categoryCode => _categoryCode;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['userProfileSess'] = _userProfileSess;
    map['bankId'] = _bankId;
    map['userId'] = _userId;
    map['orgId'] = _orgId;
    map['principalId'] = _principalId;
    if (_userType != null) {
      map['userType'] = _userType.toJson();
    }
    map['customerId'] = _customerId;
    if (_salutation != null) {
      map['salutation'] = _salutation.toJson();
    }
    map['userNickName'] = _userNickName;
    map['firstName'] = _firstName;
    map['middleName'] = _middleName;
    map['lastName'] = _lastName;
    map['primaryBranchId'] = _primaryBranchId;
    map['primaryDivisionId'] = _primaryDivisionId;
    if (_divisionAccessIndicator != null) {
      map['divisionAccessIndicator'] = _divisionAccessIndicator.toJson();
    }
    map['primaryAccountId'] = _primaryAccountId;
    map['lastTransactionDate'] = _lastTransactionDate;
    map['loginDate'] = _loginDate;
    map['logoffDate'] = _logoffDate;
    if (_limitScheme != null) {
      map['limitScheme'] = _limitScheme.toJson();
    }
    if (_rangeLimitScheme != null) {
      map['rangeLimitScheme'] = _rangeLimitScheme.toJson();
    }
    if (_loginAllowed != null) {
      map['loginAllowed'] = _loginAllowed.toJson();
    }
    if (_transactionAllowed != null) {
      map['transactionAllowed'] = _transactionAllowed.toJson();
    }
    if (_language != null) {
      map['language'] = _language.toJson();
    }
    if (_accountFormat != null) {
      map['accountFormat'] = _accountFormat.toJson();
    }
    if (_segmentName != null) {
      map['segmentName'] = _segmentName.toJson();
    }
    if (_accessScheme != null) {
      map['accessScheme'] = _accessScheme.toJson();
    }
    if (_transactionAuthorizationScheme != null) {
      map['transactionAuthorizationScheme'] = _transactionAuthorizationScheme.toJson();
    }
    if (_loginChannel != null) {
      map['loginChannel'] = _loginChannel.toJson();
    }
    if (_lastUnsuccessfullLoginChannel != null) {
      map['lastUnsuccessfullLoginChannel'] = _lastUnsuccessfullLoginChannel.toJson();
    }
    if (_dateFormat != null) {
      map['dateFormat'] = _dateFormat.toJson();
    }
    if (_calendarType != null) {
      map['calendarType'] = _calendarType.toJson();
    }
    if (_unifiedLoginUser != null) {
      map['unifiedLoginUser'] = _unifiedLoginUser.toJson();
    }
    map['lastSuccessfullLoginTime'] = _lastSuccessfullLoginTime;
    map['lastUnsuccessfullLoginTime'] = _lastUnsuccessfullLoginTime;
    map['email'] = _email;
    if (_confidentialTransactionAccess != null) {
      map['confidentialTransactionAccess'] = _confidentialTransactionAccess.toJson();
    }
    if (_prospectIndicator != null) {
      map['prospectIndicator'] = _prospectIndicator.toJson();
    }
    if (_amountFormat != null) {
      map['amountFormat'] = _amountFormat.toJson();
    }
    if (_outOfOfficePreference != null) {
      map['outOfOfficePreference'] = _outOfOfficePreference.toJson();
    }
    if (_accountMaskingRequired != null) {
      map['accountMaskingRequired'] = _accountMaskingRequired.toJson();
    }
    map['preferredTimeZone'] = _preferredTimeZone;
    map['userRoleId'] = _userRoleId;
    map['userRoleName'] = _userRoleName;
    map['mobileNumber'] = _mobileNumber;
    map['phoneNumber'] = _phoneNumber;
    map['status'] = _status;
    if (_multiCurrencyTxnAllowed != null) {
      map['multiCurrencyTxnAllowed'] = _multiCurrencyTxnAllowed.toJson();
    }
    if (_categoryCode != null) {
      map['categoryCode'] = _categoryCode.toJson();
    }
    return map;
  }

}

/// codeType : "CAT"
/// cmCode : "SOF"
/// codeDescription : "SOFTWARE"

class CategoryCode {
  CategoryCode({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  CategoryCode.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "YNFG"
/// cmCode : "Y"
/// codeDescription : "Yes"

class MultiCurrencyTxnAllowed {
  MultiCurrencyTxnAllowed({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  MultiCurrencyTxnAllowed.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "YNFG"
/// cmCode : "N"
/// codeDescription : "No"

class AccountMaskingRequired {
  AccountMaskingRequired({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  AccountMaskingRequired.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "YNFG"
/// cmCode : "N"
/// codeDescription : "No"

class OutOfOfficePreference {
  OutOfOfficePreference({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  OutOfOfficePreference.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "AFT"
/// cmCode : "0"
/// codeDescription : "Lakh Format"

class AmountFormat {
  AmountFormat({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  AmountFormat.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "YNFG"
/// cmCode : "N"
/// codeDescription : "No"

class ProspectIndicator {
  ProspectIndicator({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  ProspectIndicator.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "CTE"
/// cmCode : "N"
/// codeDescription : "No Access"

class ConfidentialTransactionAccess {
  ConfidentialTransactionAccess({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  ConfidentialTransactionAccess.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "YNFG"
/// cmCode : "N"
/// codeDescription : "No"

class UnifiedLoginUser {
  UnifiedLoginUser({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  UnifiedLoginUser.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "CALT"
/// cmCode : "GR"
/// codeDescription : "Gregorian"

class CalendarType {
  CalendarType({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  CalendarType.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "DFT"
/// cmCode : "17"
/// codeDescription : "dd-MM-yyyy"

class DateFormat {
  DateFormat({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  DateFormat.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "AIS"
/// cmCode : "I"
/// codeDescription : "Internet"

class LastUnsuccessfullLoginChannel {
  LastUnsuccessfullLoginChannel({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  LastUnsuccessfullLoginChannel.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "AIS"
/// cmCode : "I"
/// codeDescription : "Internet"

class LoginChannel {
  LoginChannel({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  LoginChannel.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "ASC"
/// cmCode : "RDF"
/// codeDescription : "ICICI Retail Default"

class TransactionAuthorizationScheme {
  TransactionAuthorizationScheme({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  TransactionAuthorizationScheme.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "AMS"
/// cmCode : "ICICIRET"
/// codeDescription : "ICICIRET"

class AccessScheme {
  AccessScheme({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  AccessScheme.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "SEG"
/// cmCode : "ICICIRET"
/// codeDescription : "ICICIRET"

class SegmentName {
  SegmentName({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  SegmentName.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "ACFT"
/// cmCode : "NNCA"
/// codeDescription : "NickName(Currency)-AccountId"

class AccountFormat {
  AccountFormat({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  AccountFormat.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "LANG"
/// cmCode : "001"
/// codeDescription : "English"

class Language {
  Language({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  Language.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "YNFG"
/// cmCode : "Y"
/// codeDescription : "Yes"

class TransactionAllowed {
  TransactionAllowed({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  TransactionAllowed.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "YNFG"
/// cmCode : "Y"
/// codeDescription : "Yes"

class LoginAllowed {
  LoginAllowed({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  LoginAllowed.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "LSH"
/// cmCode : "DRL"
/// codeDescription : "Default Retail Limit DRL"

class RangeLimitScheme {
  RangeLimitScheme({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  RangeLimitScheme.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "LSH"
/// cmCode : "DRL"
/// codeDescription : "Default Retail Limit DRL"

class LimitScheme {
  LimitScheme({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  LimitScheme.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "GLO"
/// cmCode : "G"
/// codeDescription : "Global"

class DivisionAccessIndicator {
  DivisionAccessIndicator({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  DivisionAccessIndicator.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "DSAL"
/// cmCode : "MR."
/// codeDescription : "Mr."

class Salutation {
  Salutation({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  Salutation.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// codeType : "URT"
/// cmCode : "1"
/// codeDescription : "Consumer User"

class UserType {
  UserType({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  UserType.fromJson(dynamic json) {
    _codeType = json['codeType'];
    _cmCode = json['cmCode'];
    _codeDescription = json['codeDescription'];
  }
  String _codeType;
  String _cmCode;
  String _codeDescription;

  String get codeType => _codeType;
  String get cmCode => _cmCode;
  String get codeDescription => _codeDescription;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['codeType'] = _codeType;
    map['cmCode'] = _cmCode;
    map['codeDescription'] = _codeDescription;
    return map;
  }

}

/// userId : "558222302"
/// userType : "1"
/// corporateId : "558222302"
/// isTermsandConditionsAcceptanceRequired : "N"
/// forceSignonPasswordChangeFlag : "N"
/// forceTxnPasswordChangeFlag : "N"
/// forceSecurityQuestionChangeFlag : "N"
/// isUnifiedLoginAllowed : "N"
/// uniqueId : "b5ff86ac8be3518de1c01dd2afed7da81c386ff1"
/// forceUSSDPinChangeFlag : "N"
/// forceModeChangeFlag : "N"

class User {
  User({
      String userId, 
      String userType, 
      String corporateId, 
      String isTermsandConditionsAcceptanceRequired, 
      String forceSignonPasswordChangeFlag, 
      String forceTxnPasswordChangeFlag, 
      String forceSecurityQuestionChangeFlag, 
      String isUnifiedLoginAllowed, 
      String uniqueId, 
      String forceUSSDPinChangeFlag, 
      String forceModeChangeFlag,}){
    _userId = userId;
    _userType = userType;
    _corporateId = corporateId;
    _isTermsandConditionsAcceptanceRequired = isTermsandConditionsAcceptanceRequired;
    _forceSignonPasswordChangeFlag = forceSignonPasswordChangeFlag;
    _forceTxnPasswordChangeFlag = forceTxnPasswordChangeFlag;
    _forceSecurityQuestionChangeFlag = forceSecurityQuestionChangeFlag;
    _isUnifiedLoginAllowed = isUnifiedLoginAllowed;
    _uniqueId = uniqueId;
    _forceUSSDPinChangeFlag = forceUSSDPinChangeFlag;
    _forceModeChangeFlag = forceModeChangeFlag;
}

  User.fromJson(dynamic json) {
    _userId = json['userId'];
    _userType = json['userType'];
    _corporateId = json['corporateId'];
    _isTermsandConditionsAcceptanceRequired = json['isTermsandConditionsAcceptanceRequired'];
    _forceSignonPasswordChangeFlag = json['forceSignonPasswordChangeFlag'];
    _forceTxnPasswordChangeFlag = json['forceTxnPasswordChangeFlag'];
    _forceSecurityQuestionChangeFlag = json['forceSecurityQuestionChangeFlag'];
    _isUnifiedLoginAllowed = json['isUnifiedLoginAllowed'];
    _uniqueId = json['uniqueId'];
    _forceUSSDPinChangeFlag = json['forceUSSDPinChangeFlag'];
    _forceModeChangeFlag = json['forceModeChangeFlag'];
  }
  String _userId;
  String _userType;
  String _corporateId;
  String _isTermsandConditionsAcceptanceRequired;
  String _forceSignonPasswordChangeFlag;
  String _forceTxnPasswordChangeFlag;
  String _forceSecurityQuestionChangeFlag;
  String _isUnifiedLoginAllowed;
  String _uniqueId;
  String _forceUSSDPinChangeFlag;
  String _forceModeChangeFlag;

  String get userId => _userId;
  String get userType => _userType;
  String get corporateId => _corporateId;
  String get isTermsandConditionsAcceptanceRequired => _isTermsandConditionsAcceptanceRequired;
  String get forceSignonPasswordChangeFlag => _forceSignonPasswordChangeFlag;
  String get forceTxnPasswordChangeFlag => _forceTxnPasswordChangeFlag;
  String get forceSecurityQuestionChangeFlag => _forceSecurityQuestionChangeFlag;
  String get isUnifiedLoginAllowed => _isUnifiedLoginAllowed;
  String get uniqueId => _uniqueId;
  String get forceUSSDPinChangeFlag => _forceUSSDPinChangeFlag;
  String get forceModeChangeFlag => _forceModeChangeFlag;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['userId'] = _userId;
    map['userType'] = _userType;
    map['corporateId'] = _corporateId;
    map['isTermsandConditionsAcceptanceRequired'] = _isTermsandConditionsAcceptanceRequired;
    map['forceSignonPasswordChangeFlag'] = _forceSignonPasswordChangeFlag;
    map['forceTxnPasswordChangeFlag'] = _forceTxnPasswordChangeFlag;
    map['forceSecurityQuestionChangeFlag'] = _forceSecurityQuestionChangeFlag;
    map['isUnifiedLoginAllowed'] = _isUnifiedLoginAllowed;
    map['uniqueId'] = _uniqueId;
    map['forceUSSDPinChangeFlag'] = _forceUSSDPinChangeFlag;
    map['forceModeChangeFlag'] = _forceModeChangeFlag;
    return map;
  }

}