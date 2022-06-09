import 'dart:async';

import 'package:flutter_module/api/api_wrapper.dart';
import 'package:flutter_module/api/repo.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:formz/formz.dart';

import '../../models/balance_model.dart';
import '../../models/balance_request.dart';
import '../../models/login_requesrt.dart';

part 'login_signup_event.dart';

part 'login_signup_state.dart';

class LoginSignupBloc extends Bloc<LoginSignupEvent, LoginSignupState> {
  LoginSignupBloc({
    @required Repository authenticationRepository,
  })  : _authenticationRepository = authenticationRepository,
        super(const LoginSignupState()) {
    on<LoginSubmitted>(_onSubmitted);
    on<NetBalance>(_onBal);
  }

  final Repository _authenticationRepository;

  // LoginSignupBloc(LoginSignupState initialState) : super(initialState);

  bool autoValidation = false;
  final _controller = StreamController<ApiWrapper>();

  Stream<ApiWrapper> get loginController => _controller.stream;

  //onLoginClick
  Future<bool> onLoginClick(final formKey) async {
    if (formKey.currentState.validate()) {
      formKey.currentState.save();
      return true;
    } else {
      autoValidation = true;
      return false;
    }
  }

  void _onSubmitted(
    LoginSubmitted event,
    Emitter<LoginSignupState> emit,
  ) async {
    // if (state.status.isValidated) {
    emit(state.copyWith(status: FormzStatus.submissionInProgress));
    var data1 = await _authenticationRepository.postLogin(event.mLoginRequest);
    emit(state.copyWith(mApiWrapper: data1,status: FormzStatus.submissionSuccess));
    debugPrint("-------------$data1");
    // }
  }

  void _onBal(
      NetBalance event,
    Emitter<LoginSignupState> emit,
  ) async {
    // if (state.status.isValidated) {
    emit(state.copyWith(status: FormzStatus.submissionInProgress));
    var data1 = await _authenticationRepository.getBalance(event.mBalanceModel);
    emit(state.copyWith(mApiWrapper: data1,status: FormzStatus.submissionSuccess));
    debugPrint("-------------$data1");
    // }
  }

  dispose() {}

  @override
  Stream<LoginSignupState> mapEventToState(
    LoginSignupEvent event,
  ) async* {}
}
