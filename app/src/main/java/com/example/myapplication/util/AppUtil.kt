@file:Suppress("DEPRECATION")

package com.example.myapplication.util

import android.annotation.SuppressLint
import android.content.Context
import java.util.regex.Pattern
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.app.ProgressDialog
import android.graphics.Color
import android.net.ConnectivityManager
import android.util.Patterns
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.style.StyleSpan
import android.util.Log
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.myapplication.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}

object AppUtil {
    lateinit var fileName: String
    fun chkStatus(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (connMgr.activeNetworkInfo != null
                && connMgr.activeNetworkInfo!!.isAvailable
                && connMgr.activeNetworkInfo!!.isConnected)
    }

    fun showAlert(msg: String, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle(context.getString(R.string.app_name)).setMessage(msg).setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }

    fun String.removeFirstLastChar(): String =  this.substring(1, this.length - 1)

    fun String.uppercaseFirstLetters(str: String): String {
        var prevWasWhiteSp = true
        val chars = str.toCharArray()
        for (i in chars.indices) {
            if (Character.isLetter(chars[i])) {
                if (prevWasWhiteSp) {
                    chars[i] = Character.toUpperCase(chars[i])
                }
                prevWasWhiteSp = false
            } else {
                prevWasWhiteSp = Character.isWhitespace(chars[i])
            }
        }
        return String(chars)
    }

    fun showOkayAlert(msg: String, positive: String, negative: String, context: Context, onOkay: OnOkay) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle(context.getString(R.string.app_name)).setMessage(msg)
                .setNegativeButton(negative) { dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.dismiss()
                    onOkay.onCancel()
                }
                .setPositiveButton(positive) { dialog, _ ->
                    dialog.dismiss()
                    onOkay.onOkay()
                }
        val dialog = builder.create()
        dialog.show()
    }
     fun AppCompatActivity.customDialog(title: String, onOkay: OnOkay) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
         dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.cutsom_dialog)
       // val body = dialog.findViewById(R.id.okBtn) as Button
       // body.text = title
        val yesBtn = dialog.findViewById(R.id.okBtn) as TextView
        //val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        yesBtn.setOnClickListener {
            dialog.dismiss()
            onOkay.onOkay()
        }
      //  noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }
    fun createProgressDialog(mContext: Context): ProgressDialog {
        val dialog = ProgressDialog(mContext)
        try {
            dialog.show()
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        }

        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.transparentprogresdialog)
        return dialog
    }

    fun getHalfBoldString(bold: String, context: Context): SpannableString {
        val ss1 = SpannableString(bold)
        ss1.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)), 0, ss1.length, 0)
        return ss1
    }

    fun getHalfBoldStringBold(bold: String): SpannableString {
        val ss1 = SpannableString(bold)
        ss1.setSpan(StyleSpan(Typeface.BOLD), 0, ss1.length, 0)
        return ss1
    }

    fun isEmailValid(email: String?): Boolean {
        var isValid = false
        val expression = """^[\w.-]+@([\w\-]+\.)+[A-Z]{2,4}$"""
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (matcher.matches()) {
            isValid = true
        }
        return isValid
    }

    fun isPhoneValid(strPhone: String?): Boolean {
        return if (strPhone!!.contains("00000000")) {
            false
        } else Patterns.PHONE.matcher(strPhone).matches()
    }

    fun hideKeyBoard(context: Context) {
        val view = (context as Activity).currentFocus
        if (view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun keyBoardLanguage(context: Context) {
        val view = (context as Activity).currentFocus
        if (view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            val lisii = imm.enabledInputMethodList

            for (ee in lisii) {
                val submethods = imm.getEnabledInputMethodSubtypeList(ee, true)
                for (dfsf in submethods) {
                    if (dfsf.getMode().equals("keyboard")) {

                        Log.e("-------222",dfsf.locale)
                        Log.e("-------222",dfsf.languageTag)
                    }
                }

            }
            /*  val typee=   imm.currentInputMethodSubtype
                 val locale = Locale(typee.locale)
                 val currentLanguage = locale.displayLanguage
                 Log.e("-------111",typee.languageTag)
                 Log.e("-------222",typee.locale)
                 Log.e("-------222",currentLanguage)*/
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getOutputMediaFile(type: Int, context: Context): Uri? {
        val mediaStorageDir = File(Environment.getExternalStorageDirectory(), context.getString(R.string.app_name))

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }

        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val mediaFile: File
        if (type == 1) {
            val imageStoragePath = (mediaStorageDir).toString() + " Images/"

            createDirectory(imageStoragePath)
            mediaFile = File(imageStoragePath + "IMG" + timeStamp + ".jpg")
        } else {
            return null
        }
        fileName = mediaFile.name
        return FileProvider.getUriForFile(
                context,
                context.applicationContext
                        .packageName + ".provider", mediaFile)
    }

    private fun createDirectory(filePath: String) {
        if (!File(filePath).exists()) {
            File(filePath).mkdirs()
        }
    }
}

interface OnOkay {
    fun onOkay()
    fun onCancel()
}
