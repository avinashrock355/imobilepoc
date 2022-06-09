import 'package:shared_preferences/shared_preferences.dart';

class SharePreferencesHelper {


  static SharedPreferences _prefs;
  static const String ISLOGIN = 'is_Login';
  static const String LOGIN_DATA = 'data_Login';
  static const String COKKIELIST = 'cookielist';
  // call this method from iniState() function of mainApp().
  static Future<SharedPreferences> init() async {
    _prefs ??= await SharedPreferences.getInstance();
    return _prefs;
  }

  //sets
  static Future<bool> setBool(String key, bool value) async =>
      await _prefs.setBool(key, value);

  static Future<bool> setDouble(String key, double value) async =>
      await _prefs.setDouble(key, value);

  static Future<bool> setInt(String key, int value) async =>
      await _prefs.setInt(key, value);

  static Future<bool> setString(String key, String value) async =>
      await _prefs.setString(key, value);

  static Future<bool> setStringList(String key, List<String> value) async =>
      await _prefs.setStringList(key, value);

  //gets
  static bool getBool(String key) => _prefs.getBool(key);

  static double getDouble(String key) => _prefs.getDouble(key);

  static int getInt(String key) => _prefs.getInt(key);

  static String getString(String key) => _prefs.getString(key);

  static List<String> getStringList(String key) => _prefs.getStringList(key);

  //deletes..
  static Future<bool> remove(String key) async => await _prefs.remove(key);

  static Future<bool> clear() async => await _prefs.clear();
/*
  static SharePreferencesHelper sharePreferenceHelper;



  static SharePreferencesHelper getInstant() {
    sharePreferenceHelper ??=  SharePreferencesHelper();
    return sharePreferenceHelper;
  }

  Future<String> getString(String key) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString(key);
  }

  setString(String key, String value) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString(key, value);
  }

  Future<bool> getBool(String key) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getBool(key) ?? false;
  }

  setBool(String key, bool value) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setBool(key, value);
  }

  clearPreference() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.clear();
  }*/
}
