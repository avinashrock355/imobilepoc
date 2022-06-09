import 'dart:ui';

import 'package:flutter_module/resources/colors.dart';
import 'package:flutter_module/resources/image_assets.dart';
import 'package:flutter_module/resources/strings.dart';
import 'package:flutter_module/utils/common_widgets/common_widget.dart';
import 'package:flutter/material.dart';
import 'package:need_resume/need_resume.dart';

import 'mobile_verification.dart';

class WelcomeScreen extends StatefulWidget {
  static const String routeName = "/WelcomeScreen";

  const WelcomeScreen({Key key}) : super(key: key);

  @override
  _WelcomeScreenState createState() => _WelcomeScreenState();
}

class _WelcomeScreenState extends ResumableState<WelcomeScreen> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
      backgroundColor: ColorsHelper.white6,
      body: Container(
        padding: const EdgeInsets.only(top: 10),
        alignment: Alignment.topLeft,
        child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              _topIcon(),
              _centerUI(),
              const Expanded(
                child: SizedBox(),
              ),
              _bottomUI()
            ]),
      ),
    ));
  }

  _topIcon() => topIcon(context);

  _bottomUI() => ButtonApp(context, text:StringHelperHindi.cnt,sizeFont: 16, onPressed: () {
    pushNamed(context, MobileVerification.routeName);

  } );

  _centerUI() => Container(
        margin: const EdgeInsets.only(top: 10),
        height: MediaQuery.of(context).size.height * 0.6,
        width: MediaQuery.of(context).size.width,
        decoration: decorationOragne(),
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(
                width: MediaQuery.of(context).size.width * 0.35,
                child: Image.asset(
                  ImageAssets.icicibank,
                  fit: BoxFit.fill,
                )),
            const Text(
              StringHelperHindi.oneapp1,
              style: TextStyle(
                  color: Colors.white,
                  fontSize: 30,
                  fontFamily: "Mulish",
                  fontWeight: FontWeight.bold),
            ),
            SizedBox(
                width: MediaQuery.of(context).size.width * 0.40,
                child: Image.asset(
                  ImageAssets.welcomicon,
                  fit: BoxFit.fill,
                )),
            const Text(
              StringHelperHindi.extradata,
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.white,
                fontSize: 12,
                fontFamily: "Mulish",
                fontWeight: FontWeight.bold,
              ),
            )
          ],
        ),
      );
}
