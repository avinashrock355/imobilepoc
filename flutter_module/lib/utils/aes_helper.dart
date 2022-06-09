import 'dart:convert';

import 'package:encrypt/encrypt.dart' ;
import 'package:encrypt/encrypt.dart' as enc;
import 'package:flutter/material.dart';

class AESHelper {
  static String IValue = "vySH45iu34sY8Ljk";
  static String encryptString(String plainText) {
    try {
      const secretKey = "7061737323313233";
      final _key = enc.Key.fromBase64(base64.encode(utf8.encode(secretKey)));
      final iv = IV.fromBase64(base64.encode(utf8.encode(IValue)));
      final aes = AES(_key, mode: AESMode.cbc,padding: "PKCS7");
      final mEncrypter = Encrypter(aes);
      final encrypted = mEncrypter.encrypt(plainText, iv: iv);
  //    utf8.fuse(encrypted.base64).encoder();
      base64.encode(encrypted.bytes);
      debugPrint("----------- ${ base64.encode(encrypted.bytes)}");
      debugPrint("----------- ${ encrypted.base64}");
      String enc0= base64.encode(encrypted.bytes);
      debugPrint("----------- ${base64.encode(utf8.encode(enc0))}");

      return base64.encode(utf8.encode(enc0)) ;
    } catch (error) {
      print(error);
      throw error;
    }
  }

  static String decryptCipherString(String encryptedStr, String password) {
    try {
      final secretKey = "7061737323313233";
      final _key =enc. Key.fromBase64(base64.encode(utf8.encode(secretKey)));
      final iv = IV.fromBase64(base64.encode(utf8.encode(IValue)));
      final aes = AES(_key, mode: AESMode.cbc);
      final mEncrypter = Encrypter(aes);
      return mEncrypter.decrypt64(encryptedStr, iv: iv);
    } catch (error) {
      print(error);
      throw error;
    }
  }

/*static Tuple2<Uint8List, Uint8List> _deriveKeyAndIV(
      String passphrase, Uint8List salt) {
    var password = _createUint8ListFromString(passphrase);
    Uint8List concatenatedHashes = Uint8List(0);
    Uint8List currentHash = Uint8List(0);
    bool enoughBytesForKey = false;
    Uint8List preHash = Uint8List(0);

    while (!enoughBytesForKey) {
      // int preHashLength = currentHash.length + password.length + salt.length;
      if (currentHash.length > 0)
        preHash = Uint8List.fromList(currentHash + password + salt);
      else
        preHash = Uint8List.fromList(password + salt);

      currentHash = md5.convert(preHash).bytes;
      concatenatedHashes = Uint8List.fromList(concatenatedHashes + currentHash);
      if (concatenatedHashes.length >= 48) enoughBytesForKey = true;
    }

    var keyBtyes = concatenatedHashes.sublist(0, 32);
    var ivBtyes = concatenatedHashes.sublist(32, 48);
    return new Tuple2(keyBtyes, ivBtyes);
  }*/

/*static Uint8List _createUint8ListFromString(String s) {
    var ret = new Uint8List(s.length);
    for (var i = 0; i < s.length; i++) {
      ret[i] = s.codeUnitAt(i);
    }
    return ret;
  }*/

/*static Uint8List _genRandomWithNonZero(int seedLength) {
    final random = Random.secure();
    const int randomMax = 245;
    final Uint8List uInt8list = Uint8List(seedLength);
    for (int i = 0; i < seedLength; i++) {
      uInt8list[i] = random.nextInt(randomMax) + 1;
    }
    return uInt8list;
  }*/

/*static final _key =
      Key.fromBase64(base64.encode(utf8.encode(Constant.ENC_KEY)));
  static final _iv = IV.fromBase64(base64.encode(utf8.encode(Constant.IV)));
  static final _encrypter = Encrypter(AES(
    _key,
    mode: AESMode.cbc,
  ));

  static String encrypt(String plainText) {
    var encrypted = _encrypter.encrypt(plainText, iv: _iv);
    String text = encrypted.base64;
    return text;
  }

  static decrypt(String base64String) {
    return _encrypter.decrypt64(base64String, iv: _iv);
  }*/
}
