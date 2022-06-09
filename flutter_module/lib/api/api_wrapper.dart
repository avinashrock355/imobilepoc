

// import 'package:demoflutter/api/api_error.dart';
//
import 'api_error.dart';

class ApiWrapper{

  dynamic data;
  ApiError error;
  int statusCode;
  ApiWrapper(this.data,this.statusCode,this.error);

}