import 'dart:async';

import 'package:flutter/material.dart';
import 'package:test/home.dart';

//import 'login_screen.dart';

void main() {
  runApp(SplashScreen());
}

class SplashScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => StartState();
}

class StartState extends State<SplashScreen> {

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    startTime();
  }

  startTime() async {
    var duration =const Duration(seconds: 3);
    return  Timer(duration, route);
  }

  route() {
   Navigator.pushReplacement(context,
       MaterialPageRoute(builder: (context) => Home_screen()
    ));
  }

  @override
  Widget build(BuildContext context) {
    return initWidget(context);
  }

  Widget initWidget(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Container(
            decoration:const BoxDecoration(
                color: Color(0xffF5591F),
                gradient: LinearGradient(colors: [(  Color(0xffF5591F)),
                  Color(0xffF2861E)],
                    begin: Alignment.topCenter,
                    end: Alignment.bottomCenter
                )
            ),
          ),
          Center(
            child: Container(
              child: Image.asset("images/app_logo.png"),
            ),
          )
        ],
      ),
    );
  }
}