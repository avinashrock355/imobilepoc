package com.icici.moneytransfer;


import android.widget.Toast;

public class C {

    public static void setToast(Toast sToast) {
        C.sToast = sToast;
    }

    public static Toast getToast() {
        return sToast;
    }

    private static Toast sToast;

    private static int DEVICE_WIDTH;
    private static int DEVICE_HEIGHT;
    public static final String OPEN_FROM = "open_from";
    public static final String DIALOG_PROGRESS_TEXT = "dialog_progress_text";

    public static int DAY1_START = 1;
    public static int DAY1_END = 2;
    public static int DAY2_START = 3;
    public static int DAY2_END = 4;
    public static int DAY3_START = 5;
    public static int DAY3_END = 6;
    public static int DAY4_START = 7;
    public static int DAY4_END = 8;
    public static int DAY5_START = 9;
    public static int DAY5_END = 10;
    public static int DAY6_START = 11;
    public static int DAY6_END = 12;
    public static int DAY7_START = 13;
    public static int DAY7_END = 14;

    public static final String FOR_HERE = "FOR_HERE";
    public static final String ALL_PRODUCTS = "All Products";



    public interface BundleKey {
        String onetimedata = "onetimeData";
        String onetimedataRes = "onetimedataRes";
        String balacne = "balance_t";
        String payeename = "namepayee";
        String PAYEEDATA = "payee_data";
        String USER_name = "user_name";
        String HEADER = "header";
        String NAME = "name";
        String COMPANY_SETTINGS = "company_settings";
        String OBJECT = "object";
        String TABLE_NAME = "table_name";
        String SEATS = "seats";
        String USER_LIST = "user_list";
        String LIST = "list";
        String GROUP_NAME = "group_name";
        String GROUP_ID = "group_id";
        String GROUP_All_CUSTOMER = "group_all_customer";
        String GROUP_LIST = "group_list";
        String EMAIL_LIST_STR = "emailString";
        String SELECTED = "selected";
        String GROUP_POSITION = "group_position";
        String CHILD_POSITION = "child_position";
        String REPORT_TYPE="report_type";
    }

    public interface Action {
        int CANCEL = 0;
        int SAVE = 1;
        int DELETE = 2;

    }

    public interface Via {
        int START_SIGNUP = 1;
        int FORGOT_PASSWORD = 2;
        int SETTINGS = 3;
        int AR = 4;
    }
}
