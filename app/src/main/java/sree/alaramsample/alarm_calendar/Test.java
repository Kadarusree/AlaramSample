package sree.alaramsample.alarm_calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import sree.alaramsample.R;

public class Test extends Activity {

    ListView listViewReminders;
    Button btnDismiss;
    TextView tvTime, tvDate;
    // public static int minute;
    ImageView profile_pic;

    public final String TAG = this.getClass().getSimpleName();

    private WakeLock mWakeLock;
    private MediaPlayer mPlayer;

    TextView tvDisplayName;
    private static final int WAKELOCK_TIMEOUT = 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.eventreminderscreen);

        listViewReminders = (ListView) findViewById(R.id.listViewReminders);
        btnDismiss = (Button) findViewById(R.id.btnDismiss);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvDate = (TextView) findViewById(R.id.tvDate);
        profile_pic = (ImageView) findViewById(R.id.profile_pic);


        tvDisplayName = (TextView) findViewById(R.id.tvDisplayName);

        SharedPreferences spf_Calendar = getSharedPreferences("HealthKOSCalendar", Context.MODE_PRIVATE);
        String display_Name = spf_Calendar.getString("DisplayName", "");
        tvDisplayName.setText(display_Name);


        SharedPreferences spf = getSharedPreferences("HealthKOSPatientId", Context.MODE_PRIVATE);
        String patient_id = spf.getString("patientId", "");

/*	if(ApplicationConstants.profile_pic_url!=null)
	{*/





        String name = getIntent().getStringExtra(AlarmManagerHelper_Calendar.NAME);
        String type = getIntent().getStringExtra(AlarmManagerHelper_Calendar.TYPE);
        String date = getIntent().getStringExtra(AlarmManagerHelper_Calendar.DATE);
        int timeHour = getIntent().getIntExtra(AlarmManagerHelper_Calendar.TIME_HOUR, 0);
        int timeMinute = getIntent().getIntExtra(AlarmManagerHelper_Calendar.TIME_MINUTE, 0);

        if (timeMinute <= 9) {
            tvTime.setText(timeHour + ":0" + timeMinute);
        } else {
            tvTime.setText(timeHour + ":" + timeMinute);
        }

        tvDate.setText(date);


        ArrayList<String> list_Name = new ArrayList<String>();
        list_Name.clear();

        ArrayList<String> list_Type = new ArrayList<String>();
        list_Type.clear();

        //System.out.println("/////name:"+name);

        try {
            if (name.toString().contains("~")) {
                StringTokenizer tok1 = new StringTokenizer(name, "~");
                while (tok1.hasMoreElements()) {
                    list_Name.add(tok1.nextToken());
                }
            } else {
                list_Name.add(name);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            if (type.toString().contains("~")) {
                StringTokenizer tok2 = new StringTokenizer(type, "~");
                while (tok2.hasMoreElements()) {
                    list_Type.add(tok2.nextToken());
                }
            } else {
                list_Type.add(type);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        //	list_Type.add(type);
        //System.out.println("/////list of type"+list_Type.toString());
        listViewReminders.setAdapter(new Test_Calendar_Adapter(this, list_Type, list_Name));

        btnDismiss.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Ensure wakelock release
        Runnable releaseWakelock = new Runnable() {
            @Override
            public void run() {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

                if (mWakeLock != null && mWakeLock.isHeld()) {
                    mWakeLock.release();
                }
            }
        };

        new Handler().postDelayed(releaseWakelock, WAKELOCK_TIMEOUT);


        // System.out.println(tempPojoList.size()+"=====Size");

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        super.onResume();

        // Set the window to keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        // Acquire wakelock
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        if (mWakeLock == null) {
            mWakeLock = pm.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), TAG);
        }

        if (!mWakeLock.isHeld()) {
            mWakeLock.acquire();
            Log.i(TAG, "Wakelock aquired!!");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
    }

}
