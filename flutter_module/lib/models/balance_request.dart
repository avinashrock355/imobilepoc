/// bankId : "ICI"
/// userId : "558222302"

class BalanceRequest {
  BalanceRequest({
      String bankId, 
      String userId,}){
    _bankId = bankId;
    _userId = userId;
}

  BalanceRequest.fromJson(dynamic json) {
    _bankId = json['bankId'];
    _userId = json['userId'];
  }
  String _bankId;
  String _userId;

  String get bankId => _bankId;
  String get userId => _userId;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['bankId'] = _bankId;
    map['userId'] = _userId;
    return map;
  }

}