package com.example.tiuadmin.simplysafeconusmerapp.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.tiuadmin.simplysafeconusmerapp.Fragments.VerifyOTPForgetPassword;
import com.example.tiuadmin.simplysafeconusmerapp.Fragments.VerifyOTP_Fragment;

/**
 * Created by tiuadmin on 17/01/17.
 */

    public class IncomingSMS extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {

            final Bundle bundle = intent.getExtras();
            try {
                if (bundle != null)
                {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    for (int i = 0; i < pdusObj .length; i++)
                    {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[])                                                                                                    pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                        String senderNum = phoneNumber ;
                        String message = currentMessage .getDisplayMessageBody();
                        try
                        {
                            if (senderNum .equals("VK-SIMPLY"))
                            {

                                switch (Const.OTP_VERIFICATION_MOUDLE_ID_FOR_SMS)
                                {

                                    case 0:
                                        VerifyOTPForgetPassword SmsVerifyOTPForgetPassword = new VerifyOTPForgetPassword();
                                        SmsVerifyOTPForgetPassword.setOTP(message );
                                        break;

                                    case 1:
                                        VerifyOTP_Fragment SmsVerifyOTP_Fragment = new VerifyOTP_Fragment();
                                        SmsVerifyOTP_Fragment.setOTP(message );
                                        break;


                                }

                            }
                        }
                        catch(Exception e){}

                    }
                }

            } catch (Exception e)
            {

            }
        }


}
