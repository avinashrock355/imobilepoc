part of 'login_signup_bloc.dart';

class LoginSignupState extends Equatable {
  const LoginSignupState({
    this.status = FormzStatus.pure,
   this. data
  });
  final FormzStatus status;
  final ApiWrapper data;
 // final bool isValid;

  LoginSignupState copyWith({
    FormzStatus status,ApiWrapper mApiWrapper
  }) {
    return LoginSignupState(
      status: status ?? this.status,
      data: mApiWrapper  ??this.data
    );
  }
  @override
  List<Object> get props => [status];
}

class LoginSignupInitial extends LoginSignupState {

}
