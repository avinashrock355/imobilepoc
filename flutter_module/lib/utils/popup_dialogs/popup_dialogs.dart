import 'dart:io';
import 'package:flutter/material.dart';

import '../../resources/colors.dart';
import '../../resources/strings.dart';
import '../app_navigator/app_navigator.dart';
import '../common_widgets/common_widget.dart';
import '../sharedpreference_helper/sharepreference_helper.dart';

class PopupDialogs {
  static displayMessage(BuildContext context, String message) async {
    return showDialog(
        barrierDismissible: false,
        context: context,
        builder: (context) {
          return AlertDialog(
            contentPadding: EdgeInsets.all(10),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(22.0)),
            content: Container(
              height: 200,
              width: double.infinity,
              color: ColorsHelper.whiteColor(),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  textHelper(
                     StringHelperHindi.alert,
                    fontSize: 20,
                  ),
                  const SizedBox(
                    height: 5,
                  ),
                  Text(
                    message ??
                        StringHelperHindi.server_error_please_try_again_later,
                    style: const TextStyle(fontSize: 13),
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(
                    height: 15,
                  ),
                  Container(
                    height: 1,
                    width: double.infinity,
                    color: ColorsHelper.bgColor(),
                    //primaryColor
                  ),
                  SizedBox(
                    height: 15,
                  ),
                  Row(
                    children: <Widget>[
                      //Cancel
                      Expanded(
                        child: Container(
                          height: 30.0,
                          decoration: BoxDecoration(
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey[500],
                                //offset: Offset(0.0, 1.5),
                                blurRadius: 1.5,
                              ),
                            ],
                            border: Border.all(
                                width: 2, color: ColorsHelper.bgColor()),
                            borderRadius: BorderRadius.circular(30.0),
                          ),
                          child: Material(
                            borderRadius: BorderRadius.circular(30.0),
                            color: ColorsHelper.whiteColor(),
                            child: InkWell(
                                borderRadius: BorderRadius.circular(30.0),
                                onTap: () {
                                  Navigator.maybePop(context);
                                },
                                child: Center(
                                  child: Text(StringHelperHindi.cancel,
                                      style: TextStyle(
                                        fontSize: 12,
                                        color: ColorsHelper.bgColor(),
                                      )),
                                )),
                          ),
                        ),
                      ),
                      SizedBox(
                        width: 15.0,
                      ),
                      //Retry
                      Expanded(
                          child: Container(
                        height: 30,
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(30.0),
                            color: ColorsHelper.bgColor(),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey[500],
                                blurRadius: 1.5,
                              ),
                            ]),
                        child: Material(
                          borderRadius: BorderRadius.circular(30.0),
                          color: ColorsHelper.bgColor(),
                          child: InkWell(
                              borderRadius: BorderRadius.circular(30.0),
                              onTap: () {
                                Navigator.maybePop(context);
                              },
                              child: Center(
                                child: Text(StringHelperHindi.ok,
                                    style: TextStyle(
                                      fontSize: 12,
                                      color: ColorsHelper.whiteColor(),
                                    )),
                              )),
                        ),
                      )),
                    ],
                  ),
                ],
              ),
            ),
          );
        });
  }

  static displayLogoutMessage(BuildContext context, String message) async {
    return showDialog(
        barrierDismissible: false,
        context: context,
        builder: (context) {
          return AlertDialog(
            contentPadding: EdgeInsets.all(10),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(22.0)),
            content: Container(
              height: 200,
              width: double.infinity,
              color: ColorsHelper.whiteColor(),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  textHelper(
                     StringHelperHindi.alert,
                    fontSize: 20,
                  ),
                  SizedBox(
                    height: 5,
                  ),
                  Text(
                    message ??
                        StringHelperHindi.server_error_please_try_again_later,
                    style: TextStyle(fontSize: 13),
                    textAlign: TextAlign.center,
                  ),
                  SizedBox(
                    height: 15,
                  ),
                  Container(
                    height: 1,
                    width: double.infinity,
                    color: ColorsHelper.bgColor(),
                    //primaryColor
                  ),
                  SizedBox(
                    height: 15,
                  ),
                  Row(
                    children: <Widget>[
                      //Cancel
                      Expanded(
                        child: Container(
                          height: 30.0,
                          decoration: BoxDecoration(
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey[500],
                                //offset: Offset(0.0, 1.5),
                                blurRadius: 1.5,
                              ),
                            ],
                            border: Border.all(
                                width: 2, color: ColorsHelper.bgColor()),
                            borderRadius: BorderRadius.circular(30.0),
                          ),
                          child: Material(
                            borderRadius: BorderRadius.circular(30.0),
                            color: ColorsHelper.whiteColor(),
                            child: InkWell(
                                borderRadius: BorderRadius.circular(30.0),
                                onTap: () {
                                  Navigator.maybePop(context);
                                },
                                child: Center(
                                  child: Text(StringHelperHindi.cancel,
                                      style: TextStyle(
                                        fontSize: 12,
                                        color: ColorsHelper.bgColor(),
                                      )),
                                )),
                          ),
                        ),
                      ),
                      SizedBox(
                        width: 15.0,
                      ),
                      //Retry
                      Expanded(
                          child: Container(
                        height: 30,
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(30.0),
                            color: ColorsHelper.bgColor(),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey[500],
                                blurRadius: 1.5,
                              ),
                            ]),
                        child: Material(
                          borderRadius: BorderRadius.circular(30.0),
                          color: ColorsHelper.bgColor(),
                          child: InkWell(
                              borderRadius: BorderRadius.circular(30.0),
                              onTap: () {
                                /*print("hiii");
                                SharePreferencesHelper.getInstant()
                                    .clearPreference();
                                SharePreferencesHelper.getInstant().setBool(
                                    SharePreferencesHelper.Is_Krishak_Login,
                                    false);*/
                                SharePreferencesHelper.clear();
                                Navigator.maybePop(context);
                                AppNavigator.launchLoginScreen(context);
                              },
                              child: Center(
                                child: Text(StringHelperHindi.ok,
                                    style: TextStyle(
                                      fontSize: 12,
                                      color: ColorsHelper.whiteColor(),
                                    )),
                              )),
                        ),
                      )),
                    ],
                  ),
                ],
              ),
            ),
          );
        });
  }

  static displayOnlyMessage(BuildContext context, String message) async {
    return showDialog(
        barrierDismissible: false,
        context: context,
        builder: (context) {
          return AlertDialog(
            contentPadding: EdgeInsets.all(10),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(22.0)),
            content: Container(
              height: 200,
              width: double.infinity,
              color: ColorsHelper.whiteColor(),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  textHelper(
                     StringHelperHindi.alert,
                    fontSize: 20,
                  ),
                  const SizedBox(
                    height: 5,
                  ),
                  Text(
                    message,
                    style: const TextStyle(fontSize: 13),
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(
                    height: 15,
                  ),
                  Container(
                    height: 1,
                    width: double.infinity,
                    color: ColorsHelper.bgColor(),
                    //primaryColor
                  ),
                  SizedBox(
                    height: 15,
                  ),
                  Row(
                    children: <Widget>[
                      //OK
                      Expanded(
                          child: Container(
                        height: 30,
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(30.0),
                            color: ColorsHelper.bgColor(),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey[500],
                                blurRadius: 1.5,
                              ),
                            ]),
                        child: Material(
                          borderRadius: BorderRadius.circular(30.0),
                          color: ColorsHelper.bgColor(),
                          child: InkWell(
                              borderRadius: BorderRadius.circular(30.0),
                              onTap: () {
                                Navigator.maybePop(context);
                              },
                              child: Center(
                                child: Text(StringHelperHindi.ok,
                                    style: TextStyle(
                                      fontSize: 12,
                                      color: ColorsHelper.whiteColor(),
                                    )),
                              )),
                        ),
                      )),
                    ],
                  ),
                ],
              ),
            ),
          );
        });
  }

  static displayDeleteAlert(
      {BuildContext context, String message, Function onPressed}) {
    return showDialog(
        barrierDismissible: false,
        context: context,
        builder: (context) {
          return AlertDialog(
            contentPadding: EdgeInsets.all(10),
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(22.0)),
            content: Container(
              height: 200,
              width: double.infinity,
              color: ColorsHelper.whiteColor(),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  textHelper(
                    StringHelperHindi.alert,
                    fontSize: 20,
                  ),
                  Text(
                    message,
                    style: TextStyle(fontSize: 13),
                    textAlign: TextAlign.center,
                  ),
                  SizedBox(
                    height: 15,
                  ),
                  Container(
                    height: 1,
                    width: double.infinity,
                    color: ColorsHelper.bgColor(),
                    //primaryColor
                  ),
                  SizedBox(
                    height: 15,
                  ),
                  Row(
                    children: <Widget>[
                      //Cancel
                      Expanded(
                        child: Container(
                          height: 30.0,
                          decoration: BoxDecoration(
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey[500],
                                //offset: Offset(0.0, 1.5),
                                blurRadius: 1.5,
                              ),
                            ],
                            border: Border.all(
                                width: 2, color: ColorsHelper.bgColor()),
                            borderRadius: BorderRadius.circular(30.0),
                          ),
                          child: Material(
                            borderRadius: BorderRadius.circular(30.0),
                            color: ColorsHelper.whiteColor(),
                            child: InkWell(
                                borderRadius: BorderRadius.circular(30.0),
                                onTap: () {
                                  Navigator.maybePop(context);
                                },
                                child: Center(
                                  child: Text(StringHelperHindi.cancel,
                                      style: TextStyle(
                                        fontSize: 12,
                                        color: ColorsHelper.bgColor(),
                                      )),
                                )),
                          ),
                        ),
                      ),
                      SizedBox(
                        width: 15.0,
                      ),
                      //Retry
                      Expanded(
                          child: Container(
                        height: 30,
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(30.0),
                            color: ColorsHelper.bgColor(),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey[500],
                                blurRadius: 1.5,
                              ),
                            ]),
                        child: Material(
                          borderRadius: BorderRadius.circular(30.0),
                          color: ColorsHelper.bgColor(),
                          child: InkWell(
                              borderRadius: BorderRadius.circular(30.0),
                              onTap: () {
                                onPressed();
                              },
                              child: Center(
                                child: Text(StringHelperHindi.ok,
                                    style: TextStyle(
                                      fontSize: 12,
                                      color: ColorsHelper.whiteColor(),
                                    )),
                              )),
                        ),
                      )),
                    ],
                  ),
                ],
              ),
            ),
          );
        });
  }
}
