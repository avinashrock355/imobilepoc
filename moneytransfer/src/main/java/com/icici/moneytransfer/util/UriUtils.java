package com.icici.moneytransfer.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;

public interface UriUtils {



    public static
    @Nullable
    Uri getImageUri() {
        Uri uri = null;
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "";
        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(fullPath, "APP_PATH_IMAGE");
            file.createNewFile();
            uri = getContentUriFromFile(file, false);
        } catch (Exception e) {
            Log.e("saveToExternalStorage()", e.getMessage());
        }
        return uri;
    }

    public static
    @Nullable
    Uri getTempImageUri(boolean getContentUri) {
        Uri uri = null;
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "APP_PATH_SD_CARD";
        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(fullPath, "APP_TEMP_IMAGE");
            file.createNewFile();
            uri = getContentUriFromFile(file, getContentUri);
        } catch (Exception e) {
            Log.e("saveToExternalStorage()", e.getMessage());
        }
        return uri;
    }

    public static
    @Nullable
    File getIdFile() {
        File file = null;
        String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "APP_PATH_SD_CARD";
        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file = new File(fullPath, "APP_PATH_IMAGE");
        } catch (Exception e) {
            Log.e("saveToExternalStorage()", e.getMessage());
        }
        return file;
    }


    public static Uri getContentUriFromFile(File file, boolean forceContentUri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && forceContentUri){
            return  null;
        }
          //  return FileProvider.getUriForFile(MyApplication.getInstance(), App.getInstance().getPackageName() + ".provider", file);
        else
            return Uri.fromFile(file);
    }

    public static boolean doesFileExist(@Nullable Uri uri) {
        if (uri == null)
            return false;
        File file = new File(uri.getPath());
        return file.exists() && file.isFile();
    }

    public static Uri getRealPathFromURI(Context context, Uri uri) {
        if (null == uri) return null;

        final String scheme = uri.getScheme();
        String data = null;

        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.MediaColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        if (data == null)
            return null;
        return Uri.parse(data);
    }
}
