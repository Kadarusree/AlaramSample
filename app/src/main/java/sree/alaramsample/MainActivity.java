package sree.alaramsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import sree.alaramsample.alarm_calendar.AlarmDBHelper_Calendar;
import sree.alaramsample.alarm_calendar.AlarmManagerHelper_Calendar;
import sree.alaramsample.alarm_calendar.AlarmModel_Calendar;

public class MainActivity extends AppCompatActivity {
    public AlarmDBHelper_Calendar dbHelper_calendar = new AlarmDBHelper_Calendar(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAlarm();
    }


    public void addAlarm() {

        AlarmModel_Calendar alarmDetails = new AlarmModel_Calendar();

        String dt = "10/03/2018";


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal_Alarm = Calendar.getInstance();
        Date d = null;
        try {
            d = sdf.parse(dt);
            cal_Alarm.setTime(d);
        } catch (Exception e) {
            // TODO: handle exception
        }

        alarmDetails.date = dt;
        alarmDetails.timeMinute = 17;
        alarmDetails.timeHour = 9;
        alarmDetails.name = "Test ";
        alarmDetails.type = "ALARM TEST";
        alarmDetails.repeatWeekly = false;

        if (cal_Alarm.getTime().toString().trim().contains("Sun")) {
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Mon")) {
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Tue")) {
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Wed")) {
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Thu")) {
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Fri")) {
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, true);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Sat")) {
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, true);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        }
		/*
		 * alarmDetails.setRepeatingDay(AlarmModel_Calendar.SUNDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel_Calendar.MONDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel_Calendar.TUESDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel_Calendar.WEDNESDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel_Calendar.THURSDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel_Calendar.FRDIAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel_Calendar.SATURDAY, true);
		 * alarmDetails.isEnabled = true;
		 * dbHelper_calendar.createAlarm(alarmDetails);
		 */
        // finish();

        if (dbHelper_calendar.getAlarms() != null && dbHelper_calendar.getAlarms().size() > 0) {
            AlarmManagerHelper_Calendar.setAlarms(this);
        }
    }
}
