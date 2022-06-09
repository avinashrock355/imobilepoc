import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:need_resume/need_resume.dart';

import '../resources/colors.dart';
import '../resources/image_assets.dart';
import '../resources/strings.dart';
import '../utils/common_widgets/common_widget.dart';
import 'OtpScreen.dart';

class MobileVerification extends StatefulWidget {
  static const String routeName = "/MobileVerificationScreen";

  const MobileVerification({Key key}) : super(key: key);

  @override
  _MobileVerificationState createState() => _MobileVerificationState();
}

class _MobileVerificationState extends ResumableState<MobileVerification> {
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
            Container(
                width: MediaQuery.of(context).size.width * 0.35,
                child: Image.asset(
                  ImageAssets.icicibank,
                  fit: BoxFit.fill,
                )),
            const Text(
              StringHelperHindi.mobile_veri,
              textAlign: TextAlign.center,
              style: TextStyle(
                  color: Colors.white,
                  fontSize: 30,
                  fontFamily: "Mulish",
                  fontWeight: FontWeight.bold),
            ),
            SizedBox(
                width: MediaQuery.of(context).size.width * 0.40,
                child: Image.asset(
                  ImageAssets.smsIcon,
                  fit: BoxFit.fill,
                )),
            const Text(
              StringHelperHindi.smscharge,
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

  _bottomUI() => ButtonApp(context, text: StringHelperHindi.cnt, sizeFont: 16,
          onPressed: () {
        _showBottomSheet();
      });

  _showBottomSheet() {
    showModalBottomSheet(
        context: context,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(
            top: Radius.circular(40),
          ),
        ),
        clipBehavior: Clip.antiAliasWithSaveLayer,
        builder: (builder) {
          return StatefulBuilder(
              builder: (BuildContext context, StateSetter mystate) {
            return Padding(
              padding: const EdgeInsets.all(20),
              child: Wrap(
                alignment: WrapAlignment.center,
                crossAxisAlignment: WrapCrossAlignment.center,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(5),
                    child: textHelper(
                      StringHelperHindi.simSelect,
                      textAlign: TextAlign.center,
                      textColor: ColorsHelper.textcolorb,
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(5),
                    child: textHelper(
                      StringHelperHindi.mobileSelect,
                      textAlign: TextAlign.center,
                      textColor: ColorsHelper.textcolornewgrey,
                      fontSize: 14,
                    ),
                  ),
                  _simSelection("Idea", 1, sim1Selected, () {
                    sim1Selected = true;
                    sim2Selected = false;
                    debugPrint("sim1------ $sim1Selected");
                    pushNamed(context, OtpScreen.routeName);
                    mystate(() {});
                  }),
                  _simSelection("Jio 4G", 2, sim2Selected, () {
                    sim2Selected = true;
                    sim1Selected = false;
                    pushNamed(context, OtpScreen.routeName);
                    debugPrint("sim2------ $sim2Selected");
                    mystate(() {});
                  }),
                ],
              ),
            );
          });
        });
  }

  bool sim1Selected = false;
  bool sim2Selected = false;

  Widget _simSelection(String simoprator, int number, bool isSelected,
          GestureTapCallback ontap) =>
      InkWell(
        child: Container(
          margin: const EdgeInsets.only(top: 10),
          padding: const EdgeInsets.all(5),
          decoration: BoxDecoration(
              shape: BoxShape.rectangle,
              border: Border.all(color: const Color(0xFF616571)),
              borderRadius: const BorderRadius.all(Radius.circular(20))),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              CircleAvatar(
                  radius: 30,
                  backgroundColor: ColorsHelper.textcolornewgrey,
                  child: CircleAvatar(
                    radius: 29,
                    backgroundColor: ColorsHelper.whiteColor(),
                    child: Icon(Icons.check,
                        color: isSelected
                            ? ColorsHelper.colorGreen
                            : ColorsHelper.whiteColor()),
                  )),
              Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.all(5),
                    child: textHelper("Sim $number",
                        textAlign: TextAlign.center,
                        textColor: ColorsHelper.textcolornew,
                        fontSize: 14,
                        fontWeight: FontWeight.bold),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(5),
                    child: textHelper(simoprator,
                        textAlign: TextAlign.center,
                        textColor: ColorsHelper.textcolornew,
                        fontSize: 14,
                        fontWeight: FontWeight.bold),
                  )
                ],
              ),
              const Expanded(child: SizedBox()),
              Center(
                child: Image.asset(
                  ImageAssets.sim,
                  width: 50,
                  height: 50,
                ),
              )
            ],
          ),
        ),
        onTap: ontap,
      );
}
