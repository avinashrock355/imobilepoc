import 'package:flutter_module/ui/welcome_screen.dart';
import 'package:flutter/material.dart';

import '../../ui/login.dart';
import '../../ui/mobile_verification.dart';

class AppNavigator {


  //Login Screen
  static void launchLoginScreen(BuildContext context) {
    Navigator.pushNamed(context, LoginPage.routeName);
  }
  static void launchWelcomeScreen(BuildContext context) {
    Navigator.pushNamed(context, WelcomeScreen.routeName);
  }
  static void launchMobileVerification(BuildContext context) {
    Navigator.pushNamed(context, MobileVerification.routeName);
  }
/*

  //OtpScreen
  //New password
  static void launchOtpScreen(BuildContext context,  Welcome welcomeData) {
    Navigator.pushNamed(context, OtpScreen.routeName, arguments: welcomeData);
  //  Navigator.of(context).pushNamedAndRemoveUntil(DashboardScreen.routeName, (Route<dynamic> route) => false);
  }

  //SignUp Screen
  static void launchSignupScreen(BuildContext context) {
    Navigator.pushNamed(context, SignupPage.routeName);
   // Navigator.of(context).pushNamedAndRemoveUntil(SignupPage.routeName, (Route<dynamic> route) => false);
  }

  //KrishakSwaghoshna Screen
  static void launchKrishakSwaghoshna(BuildContext context) {
    Navigator.pushNamed(context, KrishakSwaghoshna.routeName);
  }

  //KrishakSwaghoshna Add AccountScreen
  static void launchAddAccount(BuildContext context) {
    Navigator.pushNamed(context, AddAccount.routeName);
  }


  //KrishakSwaghoshna Add AccountScreen
  static void launchMosamJankari(BuildContext context) {
    Navigator.pushNamed(context, MosamJankariNew.routeName);
  }

  //KrishakSwaghoshna Add AccountScreen
  static void launchPmKisan(BuildContext context) {
    Navigator.pushNamed(context, PmKisanMain.routeName);
  }

  //KrishakSwaghoshna Add AccountScreen
  static void launchPmKisanDetailScreen(BuildContext context, PmKisanData pmKisanData) {
    Navigator.pushNamed(context, PmKisanDetailScreen.routeName, arguments: pmKisanData);
  }
  static void launchPmKisanAccountList(BuildContext context, List<PmKisanData> pmKisanData) {
    Navigator.pushNamed(context, PmKisanAccountList.routeName,  arguments: pmKisanData);
  }

  static void launchMandiRate(BuildContext context) {
    Navigator.pushNamed(context, MandiRate.routeName);
  }

  static void launchMandiDetailScreen(BuildContext context, EachMandiInfo eachMandiInfo) {
    Navigator.pushNamed(context, MandiDetailScreen.routeName, arguments: eachMandiInfo);
  }

*/

  static void popBackStack(BuildContext context) {
    Navigator.pop(context);
  }
}
