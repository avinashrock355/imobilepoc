import 'dart:convert';

/// branchDetails : [{"accountNumber":"000401198089","branchAddress":null,"ifscCode":"ICIC0000004"}]
/// currency : {"codeType":"CRN","cmCode":"INR","codeDescription":"?"}
/// allBankAccounts : {"amount":369.84,"currency":"INR"}
/// allDepositAccounts : {"amount":54654.0,"currency":"INR"}
/// totalAssets : {"amount":55023.84,"currency":"INR"}
/// totalLiabilities : {"amount":0.0,"currency":"INR"}
/// bankAccountCount : 1
/// depositAccountCount : 1

String balanceToJson(BalanceModel data) => json.encode(data.toJson());
class BalanceModel {
  BalanceModel({
      List<BranchDetails> branchDetails, 
      Currency currency, 
      AllBankAccounts allBankAccounts, 
      AllDepositAccounts allDepositAccounts, 
      TotalAssets totalAssets, 
      TotalLiabilities totalLiabilities, 
      int bankAccountCount, 
      int depositAccountCount,}){
    _branchDetails = branchDetails;
    _currency = currency;
    _allBankAccounts = allBankAccounts;
    _allDepositAccounts = allDepositAccounts;
    _totalAssets = totalAssets;
    _totalLiabilities = totalLiabilities;
    _bankAccountCount = bankAccountCount;
    _depositAccountCount = depositAccountCount;
}

  BalanceModel.fromJson(dynamic json) {
    if (json['branchDetails'] != null) {
      _branchDetails = [];
      json['branchDetails'].forEach((v) {
        _branchDetails.add(BranchDetails.fromJson(v));
      });
    }
    _currency = json['currency'] != null ? Currency.fromJson(json['currency']) : null;
    _allBankAccounts = json['allBankAccounts'] != null ? AllBankAccounts.fromJson(json['allBankAccounts']) : null;
    _allDepositAccounts = json['allDepositAccounts'] != null ? AllDepositAccounts.fromJson(json['allDepositAccounts']) : null;
    _totalAssets = json['totalAssets'] != null ? TotalAssets.fromJson(json['totalAssets']) : null;
    _totalLiabilities = json['totalLiabilities'] != null ? TotalLiabilities.fromJson(json['totalLiabilities']) : null;
    _bankAccountCount = json['bankAccountCount'];
    _depositAccountCount = json['depositAccountCount'];
  }
  List<BranchDetails> _branchDetails;
  Currency _currency;
  AllBankAccounts _allBankAccounts;
  AllDepositAccounts _allDepositAccounts;
  TotalAssets _totalAssets;
  TotalLiabilities _totalLiabilities;
  int _bankAccountCount;
  int _depositAccountCount;

  List<BranchDetails> get branchDetails => _branchDetails;
  Currency get currency => _currency;
  AllBankAccounts get allBankAccounts => _allBankAccounts;
  AllDepositAccounts get allDepositAccounts => _allDepositAccounts;
  TotalAssets get totalAssets => _totalAssets;
  TotalLiabilities get totalLiabilities => _totalLiabilities;
  int get bankAccountCount => _bankAccountCount;
  int get depositAccountCount => _depositAccountCount;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    if (_branchDetails != null) {
      map['branchDetails'] = _branchDetails.map((v) => v.toJson()).toList();
    }
    if (_currency != null) {
      map['currency'] = _currency.toJson();
    }
    if (_allBankAccounts != null) {
      map['allBankAccounts'] = _allBankAccounts.toJson();
    }
    if (_allDepositAccounts != null) {
      map['allDepositAccounts'] = _allDepositAccounts.toJson();
    }
    if (_totalAssets != null) {
      map['totalAssets'] = _totalAssets.toJson();
    }
    if (_totalLiabilities != null) {
      map['totalLiabilities'] = _totalLiabilities.toJson();
    }
    map['bankAccountCount'] = _bankAccountCount;
    map['depositAccountCount'] = _depositAccountCount;
    return map;
  }

}

/// amount : 0.0
/// currency : "INR"

class TotalLiabilities {
  TotalLiabilities({
      double amount, 
      String currency,}){
    _amount = amount;
    _currency = currency;
}

  TotalLiabilities.fromJson(dynamic json) {
    _amount = json['amount'];
    _currency = json['currency'];
  }
  double _amount;
  String _currency;

  double get amount => _amount;
  String get currency => _currency;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['amount'] = _amount;
    map['currency'] = _currency;
    return map;
  }

}

/// amount : 55023.84
/// currency : "INR"

class TotalAssets {
  TotalAssets({
      double amount, 
      String currency,}){
    _amount = amount;
    _currency = currency;
}

  TotalAssets.fromJson(dynamic json) {
    _amount = json['amount'];
    _currency = json['currency'];
  }
  double _amount;
  String _currency;

  double get amount => _amount;
  String get currency => _currency;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['amount'] = _amount;
    map['currency'] = _currency;
    return map;
  }

}

/// amount : 54654.0
/// currency : "INR"

class AllDepositAccounts {
  AllDepositAccounts({
      double amount, 
      String currency,}){
    _amount = amount;
    _currency = currency;
}

  AllDepositAccounts.fromJson(dynamic json) {
    _amount = json['amount'];
    _currency = json['currency'];
  }
  double _amount;
  String _currency;

  double get amount => _amount;
  String get currency => _currency;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['amount'] = _amount;
    map['currency'] = _currency;
    return map;
  }

}

/// amount : 369.84
/// currency : "INR"

class AllBankAccounts {
  AllBankAccounts({
      double amount, 
      String currency,}){
    _amount = amount;
    _currency = currency;
}

  AllBankAccounts.fromJson(dynamic json) {
    _amount = json['amount'];
    _currency = json['currency'];
  }
  double _amount;
  String _currency;

  double get amount => _amount;
  String get currency => _currency;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['amount'] = _amount;
    map['currency'] = _currency;
    return map;
  }

}

/// codeType : "CRN"
/// cmCode : "INR"
/// codeDescription : "?"

class Currency {
  Currency({
      String codeType, 
      String cmCode, 
      String codeDescription,}){
    _codeType = codeType;
    _cmCode = cmCode;
    _codeDescription = codeDescription;
}

  Currency.fromJson(dynamic json) {
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

/// accountNumber : "000401198089"
/// branchAddress : null
/// ifscCode : "ICIC0000004"

class BranchDetails {
  BranchDetails({
      String accountNumber, 
      dynamic branchAddress, 
      String ifscCode,}){
    _accountNumber = accountNumber;
    _branchAddress = branchAddress;
    _ifscCode = ifscCode;
}

  BranchDetails.fromJson(dynamic json) {
    _accountNumber = json['accountNumber'];
    _branchAddress = json['branchAddress'];
    _ifscCode = json['ifscCode'];
  }
  String _accountNumber;
  dynamic _branchAddress;
  String _ifscCode;

  String get accountNumber => _accountNumber;
  dynamic get branchAddress => _branchAddress;
  String get ifscCode => _ifscCode;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['accountNumber'] = _accountNumber;
    map['branchAddress'] = _branchAddress;
    map['ifscCode'] = _ifscCode;
    return map;
  }

}