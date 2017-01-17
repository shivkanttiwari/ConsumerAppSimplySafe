package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import android.content.Context;

/**
 * Created by nspl3 on 10/27/2016.
 */
public class Checkvalidation {
    static Context context=null;
    static boolean flag_validEmail=false;
    public static void MobAndPasswordValidation(Context context, String strUser, String strPass) {
        if(strUser.trim().equals("")){
            ToastMessage.toastMsgLong(context, "Please provide a mobile number");

        }
        else if(strUser.length()!=10){
            ToastMessage.toastMsgLong(context, "Mobile Number must contain 10 digits");
        }else if(strPass.length()==0){
            ToastMessage.toastMsgLong(context, "Password should not be blank");
        }

        else {
            ToastMessage.toastMsgLong(context, "Invalid username or password.");
        }
    }



    public static boolean emailValidation(Context context, String strEmail) {
        if (!strEmail.trim().equals("")) {
            if (!strEmail.contains("@")) {
//                ToastMessage.toastMsgLongEnterValidEmail(context);
                flag_validEmail = false;
            } else if (strEmail.trim().indexOf('@') == 0 || strEmail.trim().indexOf('@') == strEmail.length() - 1) {
//                ToastMessage.toastMsgLongEnterValidEmail(context);
                flag_validEmail = false;
            } else if (!strEmail.contains(".")) {
//                ToastMessage.toastMsgLongEnterValidEmail(context);
                flag_validEmail = false;
            } else if (strEmail.trim().indexOf('.') == 0 || strEmail.trim().indexOf('.') == strEmail.length() - 1) {
//                ToastMessage.toastMsgLongEnterValidEmail(context);
                flag_validEmail = false;
            } else {
                flag_validEmail = true;
            }
        }
        else
            flag_validEmail = true;

        return flag_validEmail;

    }

}
