package sree.alaramsample.alarm_calendar;

import android.provider.BaseColumns;

public final class AlarmContract {
	
	public AlarmContract() {}
	
	public static abstract class Alarm implements BaseColumns {
		public static final String TABLE_NAME = "alarm_calendar";
	    public static final String COLUMN_NAME_ALARM_NAME = "name";
		public static final String COLUMN_NAME_ALARM_TYPE = "type";
		public static final String COLUMN_NAME_ALARM_DATE = "date";
		public static final String COLUMN_NAME_ALARM_TIME_HOUR = "hour";
		public static final String COLUMN_NAME_ALARM_TIME_MINUTE = "minute";
		public static final String COLUMN_NAME_ALARM_REPEAT_DAYS = "days";
		public static final String COLUMN_NAME_ALARM_REPEAT_WEEKLY = "weekly";
		public static final String COLUMN_NAME_ALARM_ENABLED = "enabled";
	
	}
	
}
