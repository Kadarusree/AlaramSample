package sree.alaramsample.alarm_calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import sree.alaramsample.R;

public class Test_Adapter extends BaseAdapter
{
	   Test test;
	   ArrayList<String> list_Type;
	   ArrayList<String> list_Name;
	 LayoutInflater inflater;
		public Test_Adapter(Test test, ArrayList<String> list_Type, ArrayList<String> list_Name)
		{
			this.test=test;
			this.inflater= LayoutInflater.from(test);
			this.list_Name=list_Name;
			this.list_Type=list_Type;
	    }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list_Name.size();
		}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			
			convertView=inflater.inflate(R.layout.test_calendar_adapter, null);
			
			ImageView imgType=(ImageView)convertView.findViewById(R.id.imgType);
			TextView txtDescription=(TextView)convertView.findViewById(R.id.txtDescription);
			
		//	txtType.setText(list_Type.get(position));

			return convertView;
		} 

}
