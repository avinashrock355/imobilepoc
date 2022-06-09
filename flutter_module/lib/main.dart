
import 'dart:io';

import 'package:flutter_module/resources/colors.dart';
import 'package:flutter_module/utils/localizations/AppLanguage.dart';
import 'package:flutter_module/utils/my_http_overrides.dart';
import 'package:flutter_module/utils/sharedpreference_helper/sharepreference_helper.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/resources/colors.dart';
import 'package:flutter_module/utils/localizations/AppLanguage.dart';
import 'package:flutter_module/utils/my_http_overrides.dart';
import 'package:flutter_module/utils/sharedpreference_helper/sharepreference_helper.dart';

import 'app_routes.dart';

Future<void> main() async {
  HttpOverrides.global =  MyHttpOverrides();
  WidgetsFlutterBinding.ensureInitialized();
  SharePreferencesHelper.init();
  AppLanguage appLanguage = AppLanguage();
  await appLanguage.fetchLocale();
  SystemChrome.setPreferredOrientations([
    DeviceOrientation.portraitDown,
    DeviceOrientation.portraitUp,
  ]);
  SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle(
    statusBarColor: ColorsHelper.creamColor(),
    statusBarIconBrightness: Brightness.dark, //top bar icons
    systemNavigationBarColor: ColorsHelper.whiteColor(),
    systemNavigationBarIconBrightness: Brightness.dark, //bottom bar icons
  ));
  runApp(AppRotes(appLanguage: appLanguage,));
}
