import 'dart:convert';
import 'dart:typed_data';import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:provider/provider.dart';

import '../../resources/colors.dart';
import '../../resources/image_assets.dart';
import '../../resources/strings.dart';
import '../app_navigator/app_navigator.dart';
import '../localizations/AppLanguage.dart';
import '../popup_dialogs/popup_dialogs.dart';
import '../sharedpreference_helper/sharepreference_helper.dart';
class TopBar extends StatefulWidget {
    TopBar(
        this.title,
        this.show_login
      );
  final String title;
  final bool show_login;
  @override
  _TopBarState createState() => _TopBarState();
}

class _TopBarState extends State<TopBar> {

  bool isLogged = false;
  bool isLogin;


  @override
  Widget build(BuildContext context) {
    var appLanguage = Provider.of<AppLanguage>(context);
    return Padding(
      padding: EdgeInsets.only(top: 0.0),
            child: Container(
        height: 60.0,
        decoration: BoxDecoration(
          color: ColorsHelper.colorGreen,
          border: Border.all(
            width: 3.0,
            color: ColorsHelper.colorGreen,
          ),
          boxShadow: [
            BoxShadow(
              color: Colors.blueGrey.withOpacity(0.5),
              spreadRadius: 5,
              blurRadius: 3,
              offset: Offset(0, 3), // changes position of shadow
            ),
          ],
        ),

        child:Center(
          child:Container(
            child: Row(
              children: [
                SizedBox(
                  width: 5,
                ),
                widget.show_login ? Container() :
                GestureDetector(
                  child:   Padding(
                      padding: EdgeInsets.all(2.0),
                      child: Icon(Icons.arrow_back, size: 25.0, color: ColorsHelper.whiteColor(),)
                  ),
                  onTap: (){
                    Navigator.pop(context);
                  },
                ),

                 Expanded(
                  flex:1,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: [
                      const Padding(
                        padding: EdgeInsets.all(3.0),
                        child: CircleAvatar(
                          radius: 30.0,
                          backgroundImage: AssetImage(ImageAssets.logo),
                     //     backgroundImage:
                     //     NetworkImage("https://icon2.cleanpng.com/20180420/gee/kisspng-computer-icons-farmer-icon-design-clip-art-farmer-5ada50596fc531.0730372315242568574578.jpg"),
                          backgroundColor: Colors.transparent,
                        )
                      ),
                      Padding(
                          padding: EdgeInsets.all(0.0),
                          child: Text(widget.title, style: TextStyle(
                              color: ColorsHelper.whiteColor(),
                              fontSize: 16.0,
                              fontFamily: "Regular",
                              fontWeight: FontWeight.bold
                          ),)
                      ),

                    ],
                  ),
                ),

                widget.show_login ?
                isLogged ?
                GestureDetector(
                  child: Padding(
                      padding: const EdgeInsets.all(4.0),
                      child: Text(StringHelperHindi.log_out, style: TextStyle(
                          color: ColorsHelper.whiteColor(),
                          fontSize: 12.0,
                          fontFamily: "Regular",
                          fontWeight: FontWeight.bold
                      ),)
                  ),
                  onTap: (){
                    PopupDialogs.displayLogoutMessage(context, "क्या आपको यकीन है, आप लॉगआउट करना चाहते हैं?");}
                ) :
                GestureDetector(
                  child: Padding(
                      padding: const EdgeInsets.all(4.0),
                      child: Text("Yes", style: TextStyle(
                          color: ColorsHelper.whiteColor(),
                          fontSize: 12.0,
                          fontFamily: "Regular",
                          fontWeight: FontWeight.bold
                      ),)
                  ),
                  onTap: (){
                    AppNavigator.launchLoginScreen(context);
                  /*  showDialog(
                        barrierDismissible: false,
                        context: context,
                        builder: (context) {
                          return
                            LoginAlert();
                        });
*/
                               },
                )
                    : Container(),

                SizedBox(
                  width: 5.0,
                ),
                PopupMenuButton<String>(
                  color: Colors.white,
                  icon: Icon(Icons.more_vert, color: Colors.white,),
                  onSelected: (value) {
                    print(value);
                    switch (value) {
                      case 'English':
                        appLanguage.changeLanguage(Locale("en"));
                        break;
                      case 'हिंदी':
                        appLanguage.changeLanguage(Locale("hi"));
                        break;
                    }
                  },
                  itemBuilder: (BuildContext context) {
                    return {'English', 'हिंदी'}.map((String choice) {
                      return PopupMenuItem<String>(
                        value: choice,
                        child: Text(choice),
                      );
                    }).toList();
                  },
                )
              ],
            ),
          ),
        )
      //  ),
     ),
    );
  }
}
