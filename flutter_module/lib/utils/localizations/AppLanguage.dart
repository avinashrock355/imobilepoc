import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AppLanguage extends ChangeNotifier {
  Locale _appLocale = Locale('en');
  String _titleDiaryOrBudget = "iciciapp";
  String get titleDiaryOrBudget => _titleDiaryOrBudget ?? "";
  Locale get appLocal => _appLocale ?? const Locale("en");

  fetchLocale() async {
    var prefs = await SharedPreferences.getInstance();
    _titleDiaryOrBudget = "iciciapp";
    if (prefs.getString('language_code') == null) {
      _appLocale = Locale('en');
      return Null;
    }
    _appLocale = Locale(prefs.getString('language_code'));
    return Null;
  }

  void selectType(String str) {
    _titleDiaryOrBudget = str;
    notifyListeners();
  }

  void changeLanguage(Locale type) async {
    var prefs = await SharedPreferences.getInstance();
    if (_appLocale == type) {
      return;
    }
    if (type == const Locale("hi")) {
      _appLocale = const Locale("hi");
      await prefs.setString('language_code', 'hi');
      await prefs.setString('countryCode', '');
    } else {
      _appLocale = const Locale("en");
      await prefs.setString('language_code', 'en');
      await prefs.setString('countryCode', 'US');
    }
    notifyListeners();
  }
}
