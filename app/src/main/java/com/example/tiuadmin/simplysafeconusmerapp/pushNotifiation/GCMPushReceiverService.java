/*
package com.example.tiuadmin.simplysafeconusmerapp.pushNotifiation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.tiuadmin.simplysafeconusmerapp.Fragments.MainActivity;
import com.example.tiuadmin.simplysafeconusmerapp.R;
import com.google.android.gms.gcm.GcmListenerService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


*/
/**
 * Created by Belal on 4/15/2016.
 *//*


//Class is extending GcmListenerService
public class GCMPushReceiverService extends GcmListenerService {

    //This method will be called on every new message received
   // String community_id,comm_desciption,communityTitle,notificationImg,actionid;
    String title,Description,imageURL,action;
    int notificaitonID;
    Bitmap remote_picture = null;
    NotificationManager notificationManager;
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //Getting the message from the bundle

        title = data.getString("title");
        Description = data.getString("description");
        imageURL = data.getString("image");
        Description = data.getString("description");
        notificaitonID = Integer.parseInt(data.getString("id"));
        action =(data.getString("action"));


      */
/*  NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
        notiStyle.setSummaryText(Description);

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(imageURL).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        notiStyle.bigPicture(remote_picture);
        Intent intent = new Intent(this, LoginActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bold)
                .setContentTitle(""+title)
                .setContentText(Description)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Description))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setTicker("Ticker")
                .setContentIntent(pendingIntent);*//*



        NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
        notiStyle.setSummaryText(Description);

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(imageURL).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        notiStyle.bigPicture(remote_picture);
        notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = null;

        Intent gotoIntent = new Intent();
       // Intent gotoIntent = new Intent(this, LoginActivity.class);
        gotoIntent.setClassName(this, "com.example.admin.businessjodo.userActivity.LoginActivity");//Start activity when user taps on notification.
        contentIntent = PendingIntent.getActivity(this,
                (int) (Math.random() * 100), gotoIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this);
        Notification notification = mBuilder.setSmallIcon(R.drawable.logo_big).setTicker("Reload.in").setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(""+title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Description))
                .setContentIntent(contentIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

                .setContentText(Description)
                .setStyle(notiStyle).build();


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificaitonID, notiStyle.build()); //0 = ID of notification



    }

    //This method is generating a notification and displaying the notification
    private void sendNotification(String id,String commtitle,String commDesc) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_big)
                .setContentTitle(""+title)
                .setContentText(Description)
                .setAutoCancel(true)
                .setTicker("Ticker")
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificaitonID, noBuilder.build()); //0 = ID of notification


    }
}
*/
