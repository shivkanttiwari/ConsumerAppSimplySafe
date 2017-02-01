package com.example.tiuadmin.simplysafeconusmerapp.PushNotification;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.tiuadmin.simplysafeconusmerapp.Fragments.MainActivity;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Belal on 5/27/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    boolean Is_PushNotification_Enabled;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        StringBuilder builder = new StringBuilder();
        Is_PushNotification_Enabled=sharedPrefs.getBoolean("prefallowpushnotificaiton", false);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        //Calling method to generate notification
        if(Is_PushNotification_Enabled) {
            sendNotification(remoteMessage.getNotification().getBody());
        }
        else {
           // Toast.makeText(getApplicationContext(),"Notification blocked from setting",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Notification blocked from settinng: ");
        }
    }
    private void showUserSettings() {
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        StringBuilder builder = new StringBuilder();



        builder.append("\n Push notificaiotn:"
                + sharedPrefs.getBoolean("prefallowpushnotificaiton", false));

        builder.append("\n POP UP:"
                + sharedPrefs.getBoolean("prefallowpushnotificaitonpopup", false));



     /*   TextView settingsTextView = (TextView) findViewById(R.id.textUserSettings);

        settingsTextView.setText(builder.toString());*/
    }
    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Firebase Push Notification")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }



}