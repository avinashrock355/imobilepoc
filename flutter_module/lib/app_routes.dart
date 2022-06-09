import 'package:flutter_module/ui/OtpScreen.dart';
import 'package:flutter_module/ui/dashboard.dart';
import 'package:flutter_module/ui/login.dart';
import 'package:flutter_module/ui/mobile_verification.dart';
import 'package:flutter_module/ui/splash.dart';
import 'package:flutter_module/ui/welcome_screen.dart';
import 'package:flutter_module/utils/localizations/AppLanguage.dart';
import 'package:flutter_module/utils/localizations/app_localizations.dart';
import 'package:flutter_module/utils/pagerouting/slideleftroute.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'api/repo.dart';
import 'api/rest_services.dart';
import 'bloc/login_bloc/login_signup_bloc.dart';
import 'resources/colors.dart';
import 'resources/fonts.dart';
import 'resources/strings.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:provider/provider.dart';

class AppRotes extends StatelessWidget {
  final AppLanguage appLanguage;

  const AppRotes({this.appLanguage});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<AppLanguage>(
      create: (_) => appLanguage,
      child: Consumer<AppLanguage>(builder: (context, model, child) {
        return MaterialApp(
          locale: model.appLocal,
          supportedLocales: const [
            Locale('en', 'US'),
            Locale('hi', ''),
          ],
          localizationsDelegates: const [
            AppLocalizations.delegate,
            GlobalMaterialLocalizations.delegate,
            GlobalWidgetsLocalizations.delegate,
          ],
          debugShowCheckedModeBanner: false,
          title: StringHelperHindi.app_name,
          home: DashBoardPage( bloc: LoginSignupBloc(
              authenticationRepository: Repository(RestService())),),
          theme: ThemeData(
              brightness: Brightness.light,
              accentColor: ColorsHelper.creamColor(),
              primaryColor: ColorsHelper.whiteColor(),
              scaffoldBackgroundColor: ColorsHelper.whiteColor(),
              fontFamily: FontsHelper.fonts_Montserrat),
          onGenerateRoute: routes,
        );
      }),
    );
  }

  Route routes(RouteSettings settings) {
    var page;
    String routeName = settings.name;
    switch (routeName) {
      //Splash
      case Splash.routeName:
        page = Splash();
        break;
      case WelcomeScreen.routeName:
        page = const WelcomeScreen();
        break;
      case MobileVerification.routeName:
        page = const MobileVerification();
        break;
      case LoginPage.routeName:
        page = LoginPage(
          bloc: LoginSignupBloc(
              authenticationRepository: Repository(RestService())),
        );
        break;
      case OtpScreen.routeName:
        page = const OtpScreen();
        break;
      case DashBoardPage.routeName:
        page =  DashBoardPage( bloc: LoginSignupBloc(
            authenticationRepository: Repository(RestService())),);
        break;
    }

    return SlideLeftRoute(widget: page);
  }
}
