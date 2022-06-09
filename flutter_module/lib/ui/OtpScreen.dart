import 'dart:async';

import 'package:flutter_module/ui/login.dart';
import 'package:flutter_module/utils/pininput/src/pinput.dart';
import 'package:flutter/material.dart';
import 'package:need_resume/need_resume.dart';
import '../bloc/login_bloc/login_signup_bloc.dart';
import '../resources/colors.dart';
import '../resources/strings.dart';
import '../utils/common_widgets/common_widget.dart';
import '../utils/validator/validator.dart';

class OtpScreen extends StatefulWidget {
  static const String routeName = "/OtpScreen";

  const OtpScreen({
    Key key,
  }) : super(key: key);

  @override
  _OtpScreenState createState() => _OtpScreenState();
}

class _OtpScreenState extends ResumableState<OtpScreen> {
  int percent = 0;
  //LoginSignupBloc loginSignupBloc = LoginSignupBloc(null);

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  FocusNode otpNode = FocusNode();
  bool isResendOtp = false;

  TextEditingController otpController = TextEditingController();

  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    // timer.cancel();
    super.dispose();
  }

  doLogin() {
    debugPrint("----- ${controller.text} --- ${controller2.text} ");
    if (controller.text.isEmpty) {
      toast("Please enter 4 digit pin");
    } else if (controller.text.length < 4) {
      toast("Please enter 4 digit pin");
    } else if (controller2.text.isEmpty) {
      toast("Please enter confirm pin");
    } else if (controller2.text != controller.text) {
      toast(" Pin not match");
    } else {
      pushNamed(context, LoginPage.routeName);
    }
  }

  final controller = TextEditingController();
  final controller2 = TextEditingController();

  @override
  Widget build(BuildContext context) {
    // const borderColor = Color.fromRGBO(114, 178, 238, 1);
    // const fillColor = Color.fromRGBO(222, 231, 240, .57);
    return SafeArea(
        child: Scaffold(
      body: Container(
          decoration: const BoxDecoration(
            color: Colors.white,
          ),
          child: Padding(
            padding: const EdgeInsets.only(top: 15.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                    padding: const EdgeInsets.only(left: 20),
                    child: textHelper(
                      StringHelperHindi.sf,
                      textAlign: TextAlign.center,
                      textColor: ColorsHelper.colorOrange1,
                      fontSize: 35,
                      fontWeight: FontWeight.bold,
                    )),
                Padding(
                    padding:
                        const EdgeInsets.only(left: 20, top: 10, bottom: 50),
                    child: textHelper(
                      StringHelperHindi.setpif,
                      textAlign: TextAlign.center,
                      textColor: ColorsHelper.textcolorb,
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    )),
                _inpuWidget(controller),
                const Padding(
                  padding: EdgeInsets.all(10),
                  child: SizedBox(),
                ),
                _inpuWidget(controller2),
                Center(child: submitButton()),
              ],
            ),
          )),
    ));
  }

  _inpuWidget(TextEditingController controller) => Pinput(
        defaultPinTheme: defaultPinTheme,
        controller: controller,
        onTap: () {},
        onCompleted: (v) {
          debugPrint("-----$v");
        },
        onChanged: (v) {},
        showCursor: true,
        cursor: cursor,
        focusedPinTheme: defaultPinTheme.copyWith(
          height: 68,
          width: 64,
          decoration: defaultPinTheme.decoration.copyWith(
            border: Border.all(color: ColorsHelper.colorOrange),
          ),
        ),
        errorPinTheme: defaultPinTheme.copyWith(
          decoration: BoxDecoration(
            color: ColorsHelper.creamColor(),
            borderRadius: BorderRadius.circular(8),
          ),
        ),
      );
  final defaultPinTheme = PinTheme(
    width: 56,
    height: 60,
    textStyle: TextStyle(
      fontSize: 22,
      color: ColorsHelper.colorOrange,
    ),
    decoration: BoxDecoration(
      color: ColorsHelper.textcolornewgrey1,
      borderRadius: BorderRadius.circular(8),
      border: Border.all(color: Colors.transparent),
    ),
  );

  final cursor = Align(
    alignment: Alignment.bottomCenter,
    child: Container(
      width: 21,
      height: 1,
      margin: const EdgeInsets.only(bottom: 12),
      decoration: BoxDecoration(
        color: const Color.fromRGBO(137, 146, 160, 1),
        borderRadius: BorderRadius.circular(8),
      ),
    ),
  );

  //------------ krishak login -----------------
  Widget otpForm() => Form(
      key: _formKey,
      // autovalidate: loginSignupBloc.autoValidation,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.end,
        children: <Widget>[
          formTextFeild(
              context: context,
              controller: otpController,
              focusNode: otpNode,
              nextFocusNode: null,
              hintText: StringHelperHindi.enter_pin,
              textInputType: TextInputType.number,
              textInputAction: TextInputAction.done,
              validation: CommonValidator.otpValidation),
        ],
      ));

  //------------ Progress Indicator ------------
  /// Returns buffered style circular progress bar.
  Widget getBufferProgressStyle() {
    return Padding(
      padding: EdgeInsets.only(left: 110.0, right: 110.0),
      child: Container(
        height: 130,
        width: 10,
        child: const CircularProgressIndicator(),
      ),
    );
  }

  Widget submitButton() => Padding(
        padding: const EdgeInsets.all(20.0),
        child: SizedBox(
            width: MediaQuery.of(context).size.width * .8,
            child: RaisedButton(
              color: ColorsHelper.colorOrange,
              hoverColor: Colors.grey,
              textColor: Colors.white,
              shape: const StadiumBorder(),
              onPressed: () {
                doLogin();
              },
              child: const Text(StringHelperHindi.cnt),
            )),
      );
}
