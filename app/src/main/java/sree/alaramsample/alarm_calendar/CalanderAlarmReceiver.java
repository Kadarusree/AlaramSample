package sree.alaramsample.alarm_calendar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;


import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sree.alaramsample.R;


/**
 * Created by srikanthk on 8/7/2018.
 */

public class CalanderAlarmReceiver extends BroadcastReceiver {
    Context ctx;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            this.ctx = context;
            Calendar c = Calendar.getInstance();
            String todayDate = (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR);


            String date = intent.getStringExtra(AlarmManagerHelper_Calendar.DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            Date d1 = sdf.parse(todayDate);
            Date d2 = sdf.parse(date);

            if (d1.compareTo(d2) == 0) {
                        /*Intent alarmIntent = new Intent(getBaseContext(), Test.class);
                        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						alarmIntent.putExtras(intent);
						getApplication().startActivity(alarmIntent);*/

                Show_Notification("Calendar Reminder", intent, context);
                AlarmManagerHelper_Calendar.setAlarms(context);
                //i=super.onStartCommand(intent, flags, startId);
            } else {
                //System.out.println(">>>>>>>>>>>>>>>>  Else block calling...");
                //Toast.makeText(getApplicationContext(), "else...", 2000).show();
            }
        } catch (Exception e) {

        }
    }

    private void Show_Notification(String notificationMessage, Intent intent, Context ctx) {


        ////////////New Notifications Code////////////
        //**add this line**
        int requestID = (int) System.currentTimeMillis();
        Intent alarmIntent = new Intent(ctx, Test.class);
        alarmIntent.putExtras(intent);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(ctx, requestID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        String channelId = "a3";


        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(ctx, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationMessage))
                        .setContentText(notificationMessage)
                        .setAutoCancel(true)
                        .setContentTitle(ctx.getResources().getString(R.string.app_name))
                        .setContentIntent(contentIntent);
        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);



// Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "a3",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        try {
            notificationManager.notify(requestID, notificationBuilder.build());
        } catch (Exception e) {

        }

        /////
    }




}
