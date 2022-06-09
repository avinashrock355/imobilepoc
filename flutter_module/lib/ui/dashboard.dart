import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:need_resume/need_resume.dart';

import '../bloc/login_bloc/login_signup_bloc.dart';
import '../models/balance_model.dart';
import '../models/balance_request.dart';
import '../resources/colors.dart';
import '../resources/strings.dart';
import '../utils/common_widgets/common_widget.dart';
import '../utils/sharedpreference_helper/sharepreference_helper.dart';

class DashBoardPage extends StatefulWidget {
  static const String routeName = "/DashBoardPage";
  LoginSignupBloc bloc;

  DashBoardPage({Key key, @required this.bloc}) : super(key: key);

  //const DashBoardPage({Key key}) : super(key: key);

  @override
  _DashBoardPageState createState() => _DashBoardPageState();
}

class _DashBoardPageState extends ResumableState<DashBoardPage> {
  String xsrfToken = '';
  String sCookie1 = '';
  String sCookie2 = '';
  String sCookie3 = '';

  Future<void> _receiveFromHost(MethodCall call) async {
    var jData;

    try {
      if (call.method == "setToken") {
        final String data = call.arguments;
        jData = await jsonDecode(data);
        print(jData);
      }
    } on PlatformException catch (error) {
      print(error);
    }

    setState(() {
      xsrfToken = jData['xsrfToken'];
      sCookie1 = jData['Cookie1'];
      sCookie2 = jData['Cookie2'];
      sCookie3 = jData['Cookie3'];
      print("--xsrfToken  $xsrfToken");
      print("--c1  $sCookie1");
      print("--c2  $sCookie2");
      print("--c3  $sCookie3");
      List<String> list1 = [];
      list1.add(sCookie1);
      list1.add(sCookie2);
      list1.add(sCookie3);
      list1.add(xsrfToken);
      // var s = json.encde(list1);
      SharePreferencesHelper.setStringList(
          SharePreferencesHelper.COKKIELIST, list1);
      BalanceRequest mBalanceModel =
      BalanceRequest(bankId: "ICI", userId: "558222302");
      widget.bloc.add(NetBalance(mBalanceModel));
    });
  }
  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    platform.setMethodCallHandler(_receiveFromHost);


  }
var totalaccbalance="";
var savingBalance="";
  static const platform = const MethodChannel('com.icici.app/token');
var depositBalance="";
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
            BalanceModel res = data.data is BalanceModel ? data.data : null;
            if (res != null) {
              debugPrint("balance ----------${balanceToJson(res)}");
              savingBalance=   res.allBankAccounts.amount.toString();
              depositBalance=   res.allDepositAccounts.amount.toString();
              totalaccbalance=    res.totalAssets.amount.toString();
              setState(() {

              });
            }else{
              debugPrint("list----------else2");
            }
            // data.data
          }else{
            debugPrint("list----------else1");
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
                spaceS(),
                _centerUI(StringHelperHindi.tatal_balance, totalaccbalance),
                _centerUI("Total Savings balance", savingBalance),
                _centerUI("Total Deposits", depositBalance),
                _centerUI("Total Investment", "88.55 lakhs"),
              ]),
        ),
      ),
    ));
  }

  _topIcon() => topIcon(context);

  _centerUI(String label, String amount) => Container(
        width: MediaQuery.of(context).size.width,
        color: ColorsHelper.whitegrey,
        // decoration: decorationOragne(),
        padding: const EdgeInsets.only(right: 10, left: 10, top: 2),
        child: Column(
          children: [
            Card(
                elevation: 5,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(20),
                ),
                color: ColorsHelper.whiteColor(),
                margin:
                    EdgeInsets.all(MediaQuery.of(context).size.width * 0.01),
                child: Container(
                  margin: const EdgeInsets.all(15),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisAlignment: MainAxisAlignment.start,
                    mainAxisSize: MainAxisSize.max,
                    children: [
                      Row(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        mainAxisAlignment: MainAxisAlignment.start,
                        children: const [
                          Icon(
                            Icons.home,
                            color: ColorsHelper.bluecolorb,
                          ),
                        ],
                      ),
                      spaceS(),
                      textHelper(
                        label,
                        textAlign: TextAlign.start,
                        textColor: ColorsHelper.textcolornew,
                        fontSize: 14,
                        fontWeight: FontWeight.bold,
                      ),
                      spaceS(),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          textHelper(
                            "₹ $amount ",
                            textAlign: TextAlign.start,
                            textColor: ColorsHelper.textcolornewgrey,
                            fontSize: 16,
                            fontWeight: FontWeight.bold,
                          ),
                          const Icon(
                            Icons.arrow_forward_ios,
                            color: ColorsHelper.textcolornewgrey,
                            size: 15,
                          ),
                        ],
                      )
                    ],
                  ),
                )),
          ],
        ),
      );

  Widget spaceS() => SizedBox(
        width: MediaQuery.of(context).size.width * 0.35,
        height: MediaQuery.of(context).size.height * 0.015,
      );
}
