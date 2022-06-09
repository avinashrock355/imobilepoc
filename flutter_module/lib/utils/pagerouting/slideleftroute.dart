import 'package:flutter/material.dart';
class SlideLeftRoute extends PageRouteBuilder {
  final Widget widget;
  SlideLeftRoute({this.widget})
      : super(
            transitionDuration: const Duration(milliseconds: 450),
            pageBuilder: (BuildContext context, Animation<double> animation,
                Animation<double> secondaryAnimation) {
              return widget;
            },
            transitionsBuilder: (BuildContext context,
                Animation<double> animation,
                Animation<double> secondaryAnimation,
                Widget child) {
              return  SlideTransition(
                position:  Tween<Offset>(
                  begin: const Offset(1.0, 0.0),
                  end: Offset.zero,
                ).animate(
                  CurvedAnimation(
                    parent: animation,
                    curve: //Cubic(0.35, 0.91, 0.33, 0.97)
                   Curves.linearToEaseOut,
                   // reverseCurve: 
                  ),
                ),
                child: child,
              );
            });
}