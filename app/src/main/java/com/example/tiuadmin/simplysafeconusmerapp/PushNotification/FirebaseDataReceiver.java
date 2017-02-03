package com.example.tiuadmin.simplysafeconusmerapp.PushNotification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by tiuadmin on 03/02/17.
 */

public class FirebaseDataReceiver extends WakefulBroadcastReceiver {

    private final String TAG = "FirebaseDataReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "I'm in!!!");

        Bundle dataBundle = intent.getExtras();

        String text=dataBundle.getString("text");
        String message=dataBundle.getString("heading");
        String imageURL=dataBundle.getString("imageUrl");



        Log.d(TAG, dataBundle.toString());

    }
}

