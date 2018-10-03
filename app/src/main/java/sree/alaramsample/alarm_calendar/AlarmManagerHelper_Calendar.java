package sree.alaramsample.alarm_calendar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.List;

public class AlarmManagerHelper_Calendar extends BroadcastReceiver {

	public static final int	DAILY_REMINDER_REQUEST_CODE=1;

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String DATE = "date";
	public static final String TIME_HOUR = "timeHour";
	public static final String TIME_MINUTE = "timeMinute";
//	public static final String TONE = "alarmTone";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		setAlarms(context);
	}
	
	public static void setAlarms(Context context) {
		cancelAlarms(context);
		
		AlarmDBHelper_Calendar dbHelper = new AlarmDBHelper_Calendar(context);

		List<AlarmModel_Calendar> alarms =  dbHelper.getAlarms();

		if (alarms!=null){
			for (AlarmModel_Calendar alarm : alarms) {
				if (alarm.isEnabled) {

					PendingIntent pIntent = createPendingIntent(context, alarm);

					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.HOUR_OF_DAY, alarm.timeHour);
					calendar.set(Calendar.MINUTE, alarm.timeMinute);
					calendar.set(Calendar.SECOND, 00);

					//Find next time to set
					final int nowDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
					final int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
					final int nowMinute = Calendar.getInstance().get(Calendar.MINUTE);
					boolean alarmSet = false;

					//First check if it's later in the week
					for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++dayOfWeek) {
						if (alarm.getRepeatingDay(dayOfWeek - 1) && dayOfWeek >= nowDay &&
								!(dayOfWeek == nowDay && alarm.timeHour < nowHour) &&
								!(dayOfWeek == nowDay && alarm.timeHour == nowHour && alarm.timeMinute <= nowMinute)) {
							calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

							setAlarm(context, calendar, pIntent);
							alarmSet = true;
							break;
						}
					}

					//Else check if it's earlier in the week
					if (!alarmSet) {
						for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++dayOfWeek) {
							if (alarm.getRepeatingDay(dayOfWeek - 1) && dayOfWeek <= nowDay && alarm.repeatWeekly) {
								calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
								calendar.add(Calendar.WEEK_OF_YEAR, 1);

								setAlarm(context, calendar, pIntent);
								alarmSet = true;
								break;
							}
						}
					}
				}
			}
		}
		

	}
	
	@SuppressLint("NewApi")
	private static void setAlarm(Context context, Calendar calendar, PendingIntent pIntent) {
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
		} else {
			alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
		}
	}
	
	public static void cancelAlarms(Context context) {
		AlarmDBHelper_Calendar dbHelper = new AlarmDBHelper_Calendar(context);
		
		List<AlarmModel_Calendar> alarms =  dbHelper.getAlarms();
		
 		if (alarms != null) {
			for (AlarmModel_Calendar alarm : alarms) {
				if (alarm.isEnabled) {
					PendingIntent pIntent = createPendingIntent(context, alarm);
	
					AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
					alarmManager.cancel(pIntent);
				}
			}
 		}
	}

	private static PendingIntent createPendingIntent(Context context, AlarmModel_Calendar model) {
		/*Intent intent = new Intent(context, AlarmService_Calendar.class);
		intent.putExtra(ID, model.id);
		intent.putExtra(NAME, model.name);
		intent.putExtra(TYPE, model.type);
		intent.putExtra(DATE, model.date);
		intent.putExtra(TIME_HOUR, model.timeHour);
		intent.putExtra(TIME_MINUTE, model.timeMinute);
	//	intent.putExtra(TONE, model.alarmTone.toString());
		
		return PendingIntent.getService(context, (int) model.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);*/

		/*ComponentName receiver = new ComponentName(context, CalanderAlarmReceiver.class);
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(receiver,
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
				PackageManager.DONT_KILL_APP);*/

		/*Intent intent = new Intent();
		intent.setAction(ALARM_RECEIVER_INTENT_TRIGGER)
		final IntentFilter intentFilter = new IntentFilter(Action.ALARM_RECEIVER_INTENT_TRIGGER);
		intent.setClass(context, CalanderAlarmReceiver.class);


		Intent intent = new Intent();
		//intent.setClass(context,CalanderAlarmReceiver.class);
		intent.setComponent(receiver);
		intent.putExtra(ID, model.id);
		intent.putExtra(NAME, model.name);
		intent.putExtra(TYPE, model.type);
		intent.putExtra(DATE, model.date);
		intent.putExtra(TIME_HOUR, model.timeHour);
		intent.putExtra(TIME_MINUTE, model.timeMinute);
		return PendingIntent.getBroadcast(context,
				DAILY_REMINDER_REQUEST_CODE, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);*/


			Intent intent = new Intent(context, CalanderAlarmReceiver.class);
		intent.putExtra(ID, model.id);
		intent.putExtra(NAME, model.name);
		intent.putExtra(TYPE, model.type);
		intent.putExtra(DATE, model.date);
		intent.putExtra(TIME_HOUR, model.timeHour);
		intent.putExtra(TIME_MINUTE, model.timeMinute);
	//	intent.putExtra(TONE, model.alarmTone.toString());

		return PendingIntent.getBroadcast(context, (int) model.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}
}