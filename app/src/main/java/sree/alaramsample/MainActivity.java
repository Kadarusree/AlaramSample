package sree.alaramsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sree.alaramsample.alarm_calendar.AlarmDBHelper;
import sree.alaramsample.alarm_calendar.AlarmManagerHelper;
import sree.alaramsample.alarm_calendar.AlarmModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public AlarmDBHelper dbHelper_calendar = new AlarmDBHelper(this);
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAlarm();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(getApplicationContext(), account.getDisplayName(), Toast.LENGTH_LONG).show();
            // Signed in successfully, show authenticated UI.
            //    updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("", "signInResult:failed code=" + e.getStatusCode());
            // updateUI(null);
        }
    }
}
