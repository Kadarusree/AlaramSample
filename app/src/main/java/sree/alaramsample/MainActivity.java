package sree.alaramsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sree.alaramsample.alarm_calendar.AlarmDBHelper;
import sree.alaramsample.alarm_calendar.AlarmManagerHelper;
import sree.alaramsample.alarm_calendar.AlarmModel;

public class MainActivity extends AppCompatActivity {
    public AlarmDBHelper dbHelper_calendar = new AlarmDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAlarm();
    }


    public void addAlarm() {

        AlarmModel alarmDetails = new AlarmModel();

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
            alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Mon")) {
            alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.MONDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Tue")) {
            alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Wed")) {
            alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Thu")) {
            alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, true);
            alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Fri")) {
            alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, true);
            alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, false);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        } else if (cal_Alarm.getTime().toString().trim().contains("Sat")) {
            alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.MONDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, false);
            alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, true);
            alarmDetails.isEnabled = true;
            dbHelper_calendar.createAlarm(alarmDetails);
        }
		/*
		 * alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel.MONDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, true);
		 * alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, true);
		 * alarmDetails.isEnabled = true;
		 * dbHelper_calendar.createAlarm(alarmDetails);
		 */
        // finish();

        if (dbHelper_calendar.getAlarms() != null && dbHelper_calendar.getAlarms().size() > 0) {
            AlarmManagerHelper.setAlarms(this);
        }
    }
}
