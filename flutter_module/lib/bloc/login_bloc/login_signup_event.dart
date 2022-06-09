part of 'login_signup_bloc.dart';

abstract class LoginSignupEvent extends Equatable {
  const LoginSignupEvent();

  @override
  List<Object> get props => [];
}
class LoginSubmitted extends LoginSignupEvent {
  //const LoginSubmitted();
  const LoginSubmitted(this.mLoginRequest);
  final LoginRequest mLoginRequest;

  @override
  List<Object> get props => [mLoginRequest];
}
class NetBalance extends LoginSignupEvent {
  //const LoginSubmitted();
  const NetBalance(this.mBalanceModel);
  final BalanceRequest mBalanceModel;

  @override
  List<Object> get props => [mBalanceModel];
}

