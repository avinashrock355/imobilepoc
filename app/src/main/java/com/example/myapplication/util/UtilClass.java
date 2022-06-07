package com.example.myapplication.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import okio.Utf8;

public class UtilClass {

    static  public String encrypt(String value,String ivString,String key) {
        try {
            IvParameterSpec iv = new IvParameterSpec(ivString.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), 0,16,"AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
           // return android.util.Base64.encodeToString(encrypted, android.util.Base64.DEFAULT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return  new String(Base64.getEncoder().encode(Base64.getEncoder().encode(encrypted)));
            }else {
                return android.util.Base64.encodeToString(android.util.Base64.encode(encrypted, android.util.Base64.DEFAULT), android.util.Base64.DEFAULT);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


static public String  randomString(){
    String  str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    String  result="";
    for (int i = 0; i < 16; i++ ) {
       int s= (int) Math.floor(Math.random() * str.length());
        result += str.charAt(s);

    }
    return  result;
}
    public ArrayList getIMEINumbers(Context context) {

        ArrayList imeis = new ArrayList();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return imeis;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            imeis.add(tm.getDeviceId(0));
            imeis.add(tm.getDeviceId(1));
            Log.v("IMEI-1", "" + tm.getDeviceId(0));
            Log.v("IMEI-2", "" + tm.getDeviceId(1));
        } else {

            imeis.add(tm.getDeviceId());
            imeis.add(tm.getDeviceId());
            Log.v("IMEI", "" + tm.getDeviceId());
        }
        return imeis;
    }

}
