
import '../../resources/strings.dart';

class CommonValidator {

  static String mobileNumValidationWithEmpty(String value) {
    if (value.isEmpty) {
      return StringHelperHindi.error_msg_empty_mobile;
    } else if (value.length < 10) {
      return StringHelperHindi.error_msg_invalid_mobile_10_digits;
    } else if (value.length > 10) {
      return StringHelperHindi.error_msg_invalid_mobile_10_digits;
    } else {
      return null;
    }
  }

  static String mobileNumValidation(String value) {
    String pattern = r'(^(?:[+0]9)?[0-9]{10}$)';
    RegExp regExp = new RegExp(pattern);
    if (value.isNotEmpty) {
      if (value.length < 10) {
        return StringHelperHindi.error_msg_invalid_mobile_10_digits;
      } else if (value.length > 10) {
        return StringHelperHindi.error_msg_invalid_mobile_10_digits;
      }
      else if (!regExp.hasMatch(value)) {
        return StringHelperHindi.error_msg_invalid_mobile_10_digits;
      }
      else {
        return null;
      }
    } else {
      return null;
    }
  }

  static String passValidation(String value) {
    if (value.isEmpty) {
      return StringHelperHindi.error_msg_empty_pass;
    } else if (value.length < 4) {
      return StringHelperHindi.error_msg_min_length;
    } else {
      return null;
    }
  }

  static String otpValidation(String value) {
    if (value.isEmpty) {
      return StringHelperHindi.error_msg_empty_otp;
    } else if (value.length < 4) {
      return StringHelperHindi.error_msg_invalid_otp;
    } else if (value.length > 4) {
      return StringHelperHindi.error_msg_invalid_otp;
    }
    else {
      return null;
    }
  }

  static String emailValidation(String value) {
    String p =
        r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';

    if (value.isEmpty) {
      return StringHelperHindi.error_msg_empty_email;
    } else if (!(new RegExp(p).hasMatch(value))) {
      return StringHelperHindi.error_msg_invalid_email;
    } else {
      return null;
    }
  }

  static String emptyValidation(String value) {
    return value.isEmpty ? StringHelperHindi.error_empty_value : null;
  }

  static String firstNameValidation(String value) {
    return value.isEmpty ? StringHelperHindi.error_firstname_empty_value : null;
  }


  static String heartrate(String value) {
    return (60 > int.tryParse(value) && int.tryParse(value) > 100)
        ? StringHelperHindi.error_empty_value
        : null;
  }

  static String validatefullName(String value) {
    String patttern = r'(^[a-zA-Z ]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Please enter full name";
    } else if (!regExp.hasMatch(value)) {
      return "Full name must be a-z and A-Z";
    }
    return null;
  }

  static String validateMobile(String value) {
    String patttern = r'(^[0-9]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Please enter contact no";
    } else if(value.length != 10){
      return "Conatct no. must 10 digits";
    }else if (!regExp.hasMatch(value)) {
      return "Conatct no. must be digits";
    }
    return null;
  }

  static String validateidpasspaort(String value) {
    String patttern = r'(^[a-zA-Z ]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Please enter id/passport";
    }
    return null;
  }

  static String samagraIdValidationWithEmpty(String value) {
    if (value.isEmpty) {
      return StringHelperHindi.error_msg_empty_mobile;
    }  else {
      return null;
    }
  }

  static String validateCompanyName(String value) {
    String patttern = r'(^[a-zA-Z ]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Please enter company name";
    }
    return null;
  }

  static String validateAddress(String value) {
    String patttern = r'(^[a-zA-Z ]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Please enter address";
    }
    return null;
  }

  static String validateCity(String value) {
    String patttern = r'(^[a-zA-Z ]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Please enter city";
    }
    return null;
  }

  static String validateCountry(String value) {
    String patttern = r'(^[a-zA-Z ]*$)';
    RegExp regExp = new RegExp(patttern);
    if (value.length == 0) {
      return "Please enter country";
    }
    return null;
  }

}
