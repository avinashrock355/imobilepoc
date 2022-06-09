import 'package:flutter_module/api/rest_services.dart';
import 'package:flutter_module/bloc/login_bloc/login_signup_bloc.dart';
import 'package:flutter_module/constants/app_colors.dart';
import 'package:flutter_module/models/login_response.dart';
import 'package:flutter_module/ui/dashboard.dart';
import 'package:flutter_module/utils/aes_helper.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';
import 'package:intl/intl.dart' as intl;
import 'package:need_resume/need_resume.dart';

import '../api/repo.dart';
import '../models/balance_model.dart';
import '../models/balance_request.dart';
import '../models/login_requesrt.dart';
import '../resources/colors.dart';
import '../resources/image_assets.dart';
import '../resources/strings.dart';
import '../utils/common_widgets/common_widget.dart';
import '../utils/sharedpreference_helper/sharepreference_helper.dart';

class LoginPage extends StatefulWidget {
  static const String routeName = "/LoginPage";
  LoginSignupBloc bloc;

  LoginPage({Key key, @required this.bloc}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends ResumableState<LoginPage> {
  TextEditingController userIDController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
          backgroundColor: ColorsHelper.whiteColor(),
          body: BlocListener<LoginSignupBloc, LoginSignupState>(
            bloc: widget.bloc,
            listener: (context, state) {
              if (state.data != null) {
                var data = state.data;
                LoginResponse res = data.data is LoginResponse ? data.data: null;
                //LoginResponse res = data.data is LoginResponse ? data.data: null;
                if (res != null && res.xsrfToken != null) {
                  loginresToJson(res);
                  SharePreferencesHelper.setString(SharePreferencesHelper.LOGIN_DATA, loginresToJson(res));
                  SharePreferencesHelper.setBool(SharePreferencesHelper.ISLOGIN, true);
                  debugPrint("----------- ${res.xsrfToken}");
                  debugPrint("logres----------- ${res.toJson().toString()}");
                  pushNamed(context, DashBoardPage.routeName);
                }
                // data.data
              }
              debugPrint("list----------");
            },
            child: Container(
              padding: const EdgeInsets.only(top: 10),
              alignment: Alignment.topLeft,
              child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    _topIcon(),
                    _centerUI(),
                  ]),
            ),
          ),
        ));
  }

  FocusNode otpNode = FocusNode();
  FocusNode passwordNode = FocusNode();

  _topIcon() => topIcon(context);

  _centerUI() =>
      Container(
        margin: const EdgeInsets.only(top: 10),
        height: MediaQuery
            .of(context)
            .size
            .height * 0.6,
        width: MediaQuery
            .of(context)
            .size
            .width,
        decoration: decorationOragne(),
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(
                width: MediaQuery
                    .of(context)
                    .size
                    .width * 0.35,
                child: Image.asset(
                  ImageAssets.icicibank,
                  fit: BoxFit.fill,
                )),
            spaceS(),
            formTextFeild(
                context: context,
                controller: userIDController,
                focusNode: otpNode,
                nextFocusNode: passwordNode,
                hintText: StringHelperHindi.enter_userid,
                textInputType: TextInputType.number,
                textInputAction: TextInputAction.next,
                suffixIcon: true,
                sufxIconVal: Icon(
                  Icons.person,
                  color: ColorsHelper.whiteColor(),
                )),
            spaceS(),
            formTextFeild(
                context: context,
                controller: passwordController,
                focusNode: passwordNode,
                //  nextFocusNode: null,
                hintText: StringHelperHindi.enter_pass,
                textInputType: TextInputType.text,
                obscureText: true,
                textInputAction: TextInputAction.done,
                suffixIcon: true,
                sufxIconVal: Icon(
                  Icons.password,
                  color: ColorsHelper.whiteColor(),
                )),
            spaceS(),
            submitButtonNew(),
            /*spaceS(),
            (SharePreferencesHelper.getBool(SharePreferencesHelper.ISLOGIN) !=
                null &&
                SharePreferencesHelper.getBool(SharePreferencesHelper.ISLOGIN)) ?  balanceButton():Container()
         */ ],
        ),
      );

  Widget spaceS() =>
      SizedBox(
        width: MediaQuery
            .of(context)
            .size
            .width * 0.35,
        height: MediaQuery
            .of(context)
            .size
            .height * 0.02,
      );

  Widget submitButton() =>
      Padding(
        padding: const EdgeInsets.all(0.0),
        child: SizedBox(
            width: MediaQuery
                .of(context)
                .size
                .width * .3,
            child: RaisedButton(
              color: ColorsHelper.colorOrange,
              hoverColor: Colors.grey,
              textColor: Colors.white,
              shape: const StadiumBorder(),
              onPressed: () {},
              child: const Text(StringHelperHindi.cnt),
            )),
      );

  Widget submitButtonNew() =>
      BlocBuilder<LoginSignupBloc, LoginSignupState>(
        bloc: widget.bloc,
        buildWhen: (previous, current) => previous.status != current.status,
        builder: (context, state) {
          return/* state.status.isSubmissionInProgress
              ? const CircularProgressIndicator()
              : */Padding(
            padding: const EdgeInsets.all(0.0),
            child: SizedBox(
                width: MediaQuery
                    .of(context)
                    .size
                    .width * .3,
                child: RaisedButton(
                  color: ColorsHelper.whitegrey,
                  hoverColor: Colors.grey,
                  textColor: Colors.white,
                  shape: const StadiumBorder(),
                  onPressed: () {
                    var now = DateTime.now();
                    var format =
                    intl.DateFormat("MM-dd-yyyy hh:mm:ss a").format(now);
                    var timeEnc = AESHelper.encryptString(format);
                    var passEnc =
                    AESHelper.encryptString(passwordController.text);
                    print("---${passwordController.text}");
                    var mLoginRequest = LoginRequest(
                      username: "${userIDController.text}",
                      loginEventTime: timeEnc,
                      password: passEnc,
                      machineFingerprint:
                      "eyJWRVJTSU9OIjoiMi4xLjIiLCJNRlAiOnsiQnJvd3NlciI6eyJVc2VyQWdlbnQiOiJNb3ppbGxhLzUuMCAoV2luZG93cyBOVCAxMC4wOyBXaW42NDsgeDY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvOTguMC40NzU4LjgwIFNhZmFyaS81MzcuMzYgRWRnLzk4LjAuMTEwOC41MCIsIlZlbmRvciI6Ikdvb2dsZSBJbmMuIiwiVmVuZG9yU3ViSUQiOiIiLCJCdWlsZElEIjoiMjAwMzAxMDciLCJDb29raWVFbmFibGVkIjp0cnVlfSwiSUVQbHVnaW5zIjp7fSwiTmV0c2NhcGVQbHVnaW5zIjp7IlBERiBWaWV3ZXIiOiIiLCJDaHJvbWUgUERGIFZpZXdlciI6IiIsIkNocm9taXVtIFBERiBWaWV3ZXIiOiIiLCJNaWNyb3NvZnQgRWRnZSBQREYgVmlld2VyIjoiIiwiV2ViS2l0IGJ1aWx0LWluIFBERiI6IiJ9LCJTY3JlZW4iOnsiRnVsbEhlaWdodCI6NzIwLCJBdmxIZWlnaHQiOjY4MCwiRnVsbFdpZHRoIjoxMjgwLCJBdmxXaWR0aCI6MTI4MCwiQ29sb3JEZXB0aCI6MjQsIlBpeGVsRGVwdGgiOjI0fSwiU3lzdGVtIjp7IlBsYXRmb3JtIjoiV2luMzIiLCJzeXN0ZW1MYW5ndWFnZSI6ImVuLVVTIiwiVGltZXpvbmUiOi0zMzB9fSwiRXh0ZXJuYWxJUCI6IiIsIk1FU0MiOnsibWVzYyI6Im1pPTI7Y2Q9MTUwO2lkPTQ1O21lc2M9NDY1Mzk3O21lc2M9NDQ3MjYxIn19",
                      bankId: "ICI",
                      grantType: "password",
                      channelId: "",
                      clientId: "",
                      clientSecret: "",
                      customData: null,
                      deviceId: "7",
                      deviceType: "8",
                      languageId: "001",
                    );
                    widget.bloc.add(LoginSubmitted(mLoginRequest));
                  },
                  child:  textHelper(StringHelperHindi.login,textColor: ColorsHelper.colorOrange),
                )),
          );
        },
      );

  Widget balanceButton() =>
      BlocBuilder<LoginSignupBloc, LoginSignupState>(
        bloc: widget.bloc,
        buildWhen: (previous, current) => previous.status != current.status,
        builder: (context, state) {
          return state.status.isSubmissionInProgress
              ? const CircularProgressIndicator()
              : Padding(
            padding: const EdgeInsets.all(0.0),
            child: SizedBox(
                width: MediaQuery
                    .of(context)
                    .size
                    .width * .3,
                child: RaisedButton(
                  color: ColorsHelper.colorOrange,
                  hoverColor: Colors.grey,
                  textColor: Colors.white,
                  shape: const StadiumBorder(),
                  onPressed: () {
                    BalanceRequest mBalanceModel=BalanceRequest(bankId: "ICI",userId: "558222302");
                    widget.bloc.add(NetBalance(mBalanceModel));
                  },
                  child: const Text(StringHelperHindi.cnt),
                )),
          );
        },
      );

}
