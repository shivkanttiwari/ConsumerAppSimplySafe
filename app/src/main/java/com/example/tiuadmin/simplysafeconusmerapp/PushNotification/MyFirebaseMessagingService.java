package com.example.tiuadmin.simplysafeconusmerapp.PushNotification;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.tiuadmin.simplysafeconusmerapp.Fragments.MainActivity;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Belal on 5/27/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    boolean Is_PushNotification_Enabled=true;
    Bitmap remote_picture = null;
    String message;
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

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
     //   if (remoteMessage.getNotification().getBody() != null)
        {

             message=remoteMessage.getNotification().getBody();
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        //Calling method to generate notification
        if(Is_PushNotification_Enabled)
        {

            String ttile=remoteMessage.getData().get("heading");

            if(message.length()<0) {
                message = remoteMessage.getData().get("text");
            }
            String image=remoteMessage.getData().get("imageUrl");
            String id=remoteMessage.getData().get("id");
            sendNotification(ttile,message,image,id);
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
    private void sendNotification(String title,String message,String image,String id) {

       /* String messageText="";
        String imageURL="";
        String Heading="";
        String Message_id="";
        String Merchant_id="";
        String Message_type="";

        try {
            JSONObject obj = new JSONObject(messageBody);

            messageText=obj.getString("text");
            imageURL=obj.getString("imageUrl");
            Heading=obj.getString("heading");
            Message_id=obj.getString("id");
            Merchant_id=obj.getString("merchand_id");
            Message_type=obj.getString("type");
        }
        catch (Exception e)
        {
            Log.d("Push_Parsing_Exception",e.toString());
        }*/

        NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
        notiStyle.setSummaryText(message);

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(image).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image.length()>0) {
            notiStyle.bigPicture(remote_picture);
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_big)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        //Added defaults
        int defaults = 0;
        defaults |= android.app.Notification.DEFAULT_SOUND;
        defaults |= android.app.Notification.DEFAULT_VIBRATE;
        notificationBuilder.setDefaults(defaults);
        notificationBuilder.setStyle(notiStyle).build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(Integer.parseInt(id), notificationBuilder.build());





    }





}