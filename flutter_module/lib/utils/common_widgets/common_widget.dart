import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';

import '../../resources/colors.dart';
import '../../resources/image_assets.dart';

callUnfocus({@required BuildContext context}) =>
    FocusScope.of(context).requestFocus(new FocusNode());

toast(String msg) => Fluttertoast.showToast(
    msg: msg,
    backgroundColor: ColorsHelper.colorOrange,
    textColor: ColorsHelper.whiteColor());

Decoration decorationOragne() => const BoxDecoration(
    //    color: const Color(0xFFD77C1F),
    gradient: LinearGradient(
        colors: [
          Color(0xFFD77C1F),
          Color(0xFFD96031),
        ],
        begin: FractionalOffset(0.0, 0.0),
        end: FractionalOffset(1.0, 0.0),
        stops: [0.0, 1.0],
        tileMode: TileMode.clamp),
    borderRadius: BorderRadius.only(
        bottomLeft: Radius.circular(30), bottomRight: Radius.circular(30)));

Widget topIcon(BuildContext context) => Container(
    alignment: Alignment.center,
    padding: const EdgeInsets.only(left: 15, bottom: 10),
    width: MediaQuery.of(context).size.width * 0.4,
    child: Image.asset(
      ImageAssets.icicibank,
      fit: BoxFit.fill,
    ));

Widget ButtonApp(BuildContext context,
        {String text, double sizeFont, @required VoidCallback onPressed}) =>
    Center(
      child: Container(
          padding: const EdgeInsets.all(10),
          width: MediaQuery.of(context).size.width * 0.9,
          child: ElevatedButton(
            onPressed: onPressed,
            child: Text(
              text,
              style: TextStyle(
                  color: ColorsHelper.whiteColor(),
                  fontSize: sizeFont,
                  fontFamily: "Mulish",
                  fontWeight: FontWeight.bold),
            ),
            style: ElevatedButton.styleFrom(
                primary: ColorsHelper.colorOrange,
                shape: const StadiumBorder()),
          )),
    );

Widget assetImageHelper({String image, double height, double width}) =>
    Container(
      height: height,
      width: width,
      child: Image.asset(
        image,
        fit: BoxFit.fill,
      ),
    );

Widget appBarBody(
        {String title,
        Color textColor,
        double fontSize = 18,
        FontWeight fontWeight = FontWeight.normal,
        bool isLeading = false,
        String leadingIcon,
        Function onTapLeading,
        String action1Icon,
        Function onTapaction1,
        String action2Icon,
        Function onTapaction2,
        Color bgColor = Colors.transparent,
        double elevation = 0}) =>
    AppBar(
     // brightness: Brightness.light,
      backgroundColor: bgColor,
      elevation: elevation,
      centerTitle: false,
      //title
      title: Text(
        title,
        style: TextStyle(
            fontSize: fontSize,
            color: textColor ?? Colors.black,
            fontWeight: fontWeight),
        textAlign: TextAlign.center,
      ),
      automaticallyImplyLeading: isLeading,
      //leading Icon
      leading: isLeading
          ? InkWell(
              onTap: onTapLeading,
              child: leadingIcon != null
                  ? Container(
                      margin: EdgeInsets.all(13),
                      child: Image.asset(
                        leadingIcon,
                      ))
                  : Container(
                      padding: EdgeInsets.symmetric(horizontal: 0, vertical: 0),
                      margin: EdgeInsets.all(10),
                      child: Icon(
                        Icons.arrow_back_ios,
                        color: Colors.black,
                      ),
                    ),
            )
          : null,
      //Action icon
      actions: <Widget>[
        action1Icon != null
            ? InkWell(
                borderRadius: BorderRadius.circular(30),
                onTap: onTapaction1,
                child: Container(
                    width: 35,
                    padding: const EdgeInsets.all(5),
                    child: Image.asset(
                      action1Icon,
                    )),
              )
            : const SizedBox(
                height: 0,
                width: 0,
              ),
        const SizedBox(
          width: 5,
        ),
        action2Icon != null
            ? InkWell(
                borderRadius: BorderRadius.circular(50),
                onTap: onTapaction2,
                child: Container(
                    width: 35,
                    padding: EdgeInsets.all(5),
                    child: Image.asset(
                      action2Icon,
                    )),
              )
            : const SizedBox(
                height: 0,
                width: 0,
              ),
        const SizedBox(
          width: 15,
        )
      ],
    );

Widget formTextFeild(
        {String hintText,
        String validation(String val),
        String save(String val),
        String onChanged(String val),
        TextEditingController controller,
        FocusNode focusNode,
        FocusNode nextFocusNode,
        textInputType = TextInputType.text,
        textCapitalization = TextCapitalization.sentences,
        TextInputAction textInputAction,
        int maxLine = 1,
        int maxLength = 150,
        bool obscureText = false,
        bool suffixIcon = false,
        Icon sufxIconVal,
        Function suffixFuncton,
        Color color = Colors.white,
        BuildContext context}) =>
    Padding(
      padding:
          const EdgeInsets.only(top: 0.0, bottom: 5.0, right: 20.0, left: 20.0),
      child: Container(
        alignment: Alignment.center,
        height: 60.0,
        child: TextFormField(
          textAlign: TextAlign.left,
          scrollPadding: EdgeInsets.all(20),
          maxLines: maxLine,
          inputFormatters: [
            LengthLimitingTextInputFormatter(maxLength),
          ],
          style: TextStyle(
              color: color,
              fontSize: 15,
              fontFamily: "Mulish",
              fontWeight: FontWeight.bold),
          onChanged: onChanged,
          validator: validation,
          onSaved: save,
          obscureText: obscureText,
          controller: controller,
          focusNode: focusNode,
          keyboardType: textInputType,
          textInputAction: textInputAction,
          onFieldSubmitted: (str) => {
            nextFocusNode != null
                ? FocusScope.of(context).requestFocus(nextFocusNode)
                : FocusScope.of(context).requestFocus(FocusNode()),
          },
          decoration: InputDecoration(
            hintMaxLines: 1,
            //  hintText: hintText,
            contentPadding: const EdgeInsets.only(left: 25.0, top: 10.0),
            labelText: hintText,
            labelStyle: TextStyle(
                color: color,
                fontSize: 15,
                fontFamily: "Mulish",
                fontWeight: FontWeight.bold),
            hintStyle:
                TextStyle(color: color, fontSize: 12, fontFamily: "Mulish"),
            // contentPadding: EdgeInsets.symmetric(vertical: 10.0, horizontal: 10.0),
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(25.0),
              borderSide: BorderSide(color: Colors.white),
            ),
            focusedBorder: OutlineInputBorder(
              borderRadius: BorderRadius.circular(25.0),
              borderSide: BorderSide(color: Colors.white),
            ),
            enabledBorder: OutlineInputBorder(
              borderRadius: BorderRadius.circular(25.0),
              borderSide: const BorderSide(
                color: Colors.white,
                width: 1.0,
              ),
            ),
            suffixIcon: suffixIcon
                ? Container(
                    child: Transform.scale(
                      scale: 0.65,
                      child: IconButton(
                        onPressed: suffixFuncton,
                        icon: sufxIconVal,
                        iconSize: 30.0,
                      ),
                    ),
                  )
                : null,
          ),
        ),
      ),
    );

//-------- submit button ----------

Widget textHelper(String title,
        {Color textColor = Colors.black,
        //const Color(0xff333333),
        double fontSize,
        bool isItalic = false,
        FontWeight fontWeight = FontWeight.normal,
        TextAlign textAlign = TextAlign.center}) =>
    Text(
      title,
      style: TextStyle(
          fontSize: fontSize,
          color: textColor,
          fontFamily: "Mulish",
          fontWeight: fontWeight,
          fontStyle: isItalic ? FontStyle.italic : FontStyle.normal),
      textAlign: textAlign,
    );
