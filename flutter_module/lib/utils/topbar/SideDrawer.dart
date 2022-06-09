import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../resources/colors.dart';


class SideDrawer extends StatefulWidget {

  @override
  SideDrawerState createState() => SideDrawerState();
}
class SideDrawerState extends State<SideDrawer> {

  @override
  void initState() {
    super.initState();

  }


  @override
  Widget build(BuildContext context) {
    return  SizedBox(
      width: MediaQuery.of(context).size.width * 0.85,//20.0,
      child:
      Container(
        decoration: BoxDecoration(
          color: ColorsHelper.whiteColor(),
          border: Border.all(
            width: 3.0,
            color: ColorsHelper.creamColor(),
          ),
        ),
        child:  Drawer(
            child:  ListView(
              children: <Widget>[
                 DrawerHeader(
                  child: Column(
                    children: const [
                      Padding(
                        padding: EdgeInsets.only(top: 20.0),
                        child:   CircleAvatar(
                          radius: 30.0,
                          backgroundImage:
                          NetworkImage("https://icon2.cleanpng.com/20180420/gee/kisspng-computer-icons-farmer-icon-design-clip-art-farmer-5ada50596fc531.0730372315242568574578.jpg"),
                          backgroundColor: Colors.transparent,
                        ),
                      ),
                    ],
                  ),
                  decoration:  BoxDecoration(color: ColorsHelper.colorGreen),
                ),
                 Padding(
                  padding: EdgeInsets.all(0.0),
                  child:
                   Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [

                      Padding(
                        padding: EdgeInsets.only(left :10.0, right: 10.0, top: 10.0),
                        child: GestureDetector(
                          onTap: (){

                          },
                          child: Row(
                            children: const [
                              Icon(Icons.settings, color: ColorsHelper.colorGreen,size: 25.0,),
                              SizedBox(width: 10.0,),
                               Text("Live",style: TextStyle(color: Colors.black87, fontSize: 14.0,fontFamily: "Regular",fontWeight: FontWeight.bold ),)
                            ],
                          ),
                        )
                      ),
                    ],
                  ),
                ),
                 Padding(
                  padding: EdgeInsets.all(10.0),
                  child:
                   Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Padding(
                        padding: EdgeInsets.only(top: 0.0, left: 0.0),
                        child: Text("", style: TextStyle(color: Colors.black, fontSize: 18.0,fontFamily: "Regular",fontWeight: FontWeight.bold ),
                        ),
                      ),

                      Padding(
                          padding: EdgeInsets.only(left :0.0, right: 10.0, top: 5.0),
                          child: GestureDetector(
                            onTap: (){

                            },
                            child: Row(
                              children: [
                                Icon(Icons.settings, color: ColorsHelper.colorGreen,size: 25.0,),
                                SizedBox(width: 10.0,),
                                 Text("",style: TextStyle(color: Colors.black87, fontSize: 14.0,fontFamily: "Regular",fontWeight: FontWeight.bold ),)
                              ],
                            ),
                          )
                        ),
                      //AllBids()
                      Padding(
                          padding: EdgeInsets.only(left :0.0, right: 10.0, top: 5.0),
                          child: GestureDetector(
                            onTap: (){

                            },
                            child: Row(
                              children: [
                                Icon(Icons.settings, color: ColorsHelper.colorGreen,size: 25.0,),
                                SizedBox(width: 10.0,),
                                 Text("",style: TextStyle(color: Colors.black87, fontSize: 14.0,fontFamily: "Regular",fontWeight: FontWeight.bold ),)
                              ],
                            ),
                          )
                      ),
                      Padding(
                          padding: EdgeInsets.only(left :0.0, right: 10.0, top: 5.0),
                          child: GestureDetector(
                            onTap: (){

                            },
                            child: Row(
                              children: [
                                Icon(Icons.settings, color: ColorsHelper.colorGreen,size: 25.0,),
                                SizedBox(width: 10.0,),
                                 Text("",style: TextStyle(color: Colors.black87, fontSize: 14.0,fontFamily: "Regular",fontWeight: FontWeight.bold ),)
                              ],
                            ),
                          )
                      ),
                    ],
                  ),
                ),
                 Padding(
                  padding: EdgeInsets.all(10.0),
                  child:
                   Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Padding(
                        padding: EdgeInsets.only(top: 0.0, left: 0.0),
                        child: Text("", style: TextStyle(color: Colors.black, fontSize: 18.0,fontFamily: "Regular",fontWeight: FontWeight.bold ),
                        ),
                      ),

                    ],
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(top: 10.0),
                  child: Container(
                    height: 50.0,
                    decoration: BoxDecoration(
                      color: ColorsHelper.whiteColor(),
                      border: Border.all(
                        width: 3.0,
                        color: ColorsHelper.colorGreen,
                      ),
                    ),
                    child:   Padding(
                      padding: EdgeInsets.only(left :10.0, right: 10.0, top: 5.0),
                      child:
                      GestureDetector(
                        onTap: (){
                          Navigator.pop(context);
                                 },
                        child: Row(
                          children: [
                            Icon(Icons.logout, color: ColorsHelper.colorGreen,size: 25.0,),
                            SizedBox(width: 10.0,),
                             Text("Logout", style: TextStyle(color: Colors.black54, fontSize: 18.0,fontFamily: "Regular",fontWeight: FontWeight.bold ),),
                          ],
                        ),
                      )

                    ),
                  ),
                )
              ],
            )),
      ),

    );
  }
}