import 'dart:async';
import 'package:permission_handler/permission_handler.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../resources/colors.dart';
import '../resources/image_assets.dart';
import '../resources/strings.dart';
import '../utils/app_navigator/app_navigator.dart';
import '../utils/common_widgets/common_widget.dart';

class Splash extends StatefulWidget {
  static const String routeName = "/";

  Splash({Key key}) : super(key: key);

  @override
  _SplashState createState() => _SplashState();
}

class _SplashState extends State<Splash> {
  bool isLogin = true;
  bool isSecondTime = false;

  @override
  void initState() {
    //callData();
    Future.delayed(const Duration(milliseconds: 2000), () async {
      launchLoginOrDashboardPage(context);
    });
    super.initState();
    // You can request multiple permissions at once.
    [
      Permission.sms,
      Permission.phone,
    ].request();
    //
  }

  void launchLoginOrDashboardPage(BuildContext context) async {
    //   isLogin = await SharePreferencesHelper.getInstant().getBool(SharePreferencesHelper.Is_Login);
    if (isLogin) {
         AppNavigator.popBackStack(context);
        AppNavigator.launchWelcomeScreen(context);

    }

  }

  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(const SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      statusBarIconBrightness: Brightness.light,
      systemNavigationBarColor: Colors.transparent,
      systemNavigationBarIconBrightness: Brightness.dark,
    ));
    return SafeArea(
        child: Scaffold(
            backgroundColor: ColorsHelper.whiteColor(),
            body: Container(
              decoration: const BoxDecoration(
                  gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [
                  Color.fromARGB(255, 255, 255, 255),
                  Color.fromARGB(255, 255, 255, 255)
                ],
              )),
              child: Center(
                  child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Padding(
                      padding: EdgeInsets.only(
                        top: MediaQuery.of(context).size.height * 0.2,
                      )),
                  Container(
                    width: MediaQuery.of(context).size.width * 0.5,
                    child: Image.asset(
                      ImageAssets.icicibank,
                      fit: BoxFit.fill,
                    ),
                  ),/*(image: ImageAssets.icicibank, height: 100),*/
                  const Padding(
                      padding: EdgeInsets.only(
                    top: 55,
                  )),
                  const Text(
                    StringHelperHindi.oneapp,
                    style: TextStyle(
                        color: Color(0xFF005A77),
                        fontSize: 24,
                        fontWeight: FontWeight.bold),
                  )
                ],
              )),
            )));
  }
}
