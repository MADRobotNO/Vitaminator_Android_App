package no.madrobot.vitaminator;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import Data.DbAlarmHandler;
import Data.DbHand;
import Data.DbStartHandler;
import Model.Medicine;

import org.joda.time.DateTime;

public class MainMenu extends AppCompatActivity {

    private Button check_status_button;
    private Button take_a_pill_button;
    private Button add_a_pill_button;
    private Button remove_a_pill_button;
    private Button exit_button;
    private ImageButton alarmButton;
    private Date date;

    private TextView todayText;

    public Context context;

    DateTime notif_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        final DbHand db = new DbHand(this);
        DbStartHandler dbStart = new DbStartHandler(this);

        final DateTime nowDate = new DateTime();

        int nowDays = nowDate.getDayOfYear();

        int lastDays = dbStart.getDate();

        if (nowDays != lastDays){
                dbStart.addDate(nowDays);

            //updating status if new day
            for (int i = 0; i<db.getAllMed().size(); i++){
                Medicine med = db.getAllMed().get(i);
                med.setStatus(0);
                db.updateStatus(med);
            }

        }

        else{
            //nothing is happening
        }

        alarmButton = findViewById(R.id.alarmButtonID);

        final DbAlarmHandler dbAlarmHandler = new DbAlarmHandler(this);
        final int stat = dbAlarmHandler.getStatus();

        if (stat == 1){
            alarmButton.setColorFilter(Color.GREEN);
            Log.d("TimePicker", String.valueOf(stat));
        }
        else{
            alarmButton.clearColorFilter();
            Log.d("TimePicker", String.valueOf(stat));
        }

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                final Intent intent = new Intent(context, AlertReciever.class);
                final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);

                final Dialog d = new Dialog(context);
                d.setTitle("Pick a time for reminder");
                d.setContentView(R.layout.dialog_time_picker);
                d.setCancelable(true);
                final Button setTimeBut = (Button) d.findViewById(R.id.setTimeButID);
                final Button cancelTimeBut = (Button) d.findViewById(R.id.cancelAlarm);
                final TimePicker timePicker = (TimePicker) d.findViewById(R.id.timePickerID);
                d.show();

                setTimeBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notif_date = new DateTime()
                                .withHourOfDay(timePicker.getHour())
                                .withMinuteOfHour(timePicker.getMinute())
                                .withSecondOfMinute(1);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notif_date.getMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                        d.dismiss();
                        alarmButton.setColorFilter(Color.GREEN);
                        String picked_hour, picked_min;
                        picked_hour = String.valueOf(notif_date.getHourOfDay());
                        picked_min = String.valueOf(notif_date.getMinuteOfHour());
                        if (notif_date.getMinuteOfHour() < 10){
                            picked_min = "0" + String.valueOf(notif_date.getMinuteOfHour());
                        }

                        dbAlarmHandler.addStatus(1);
                        Toast.makeText(MainMenu.this, "Reminder is set to " + picked_hour +":"+ picked_min , Toast.LENGTH_LONG).show();

                    }

                });
                cancelTimeBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alarmManager.cancel(pendingIntent);
                        dbAlarmHandler.addStatus(0);
                        d.dismiss();
                        alarmButton.clearColorFilter();
                        Toast.makeText(MainMenu.this, "Reminder is off" , Toast.LENGTH_LONG).show();
                    }
                });

                if (dbAlarmHandler.getStatus() == 0){
                    Log.d("TimePicker", "Set cancelTimeButton clickable to false");
                    cancelTimeBut.setClickable(false);
                }
            }
        });

        check_status_button = findViewById(R.id.check_status_button_id);
        check_status_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainMenu.this, Check_daily_status.class));
            }
        });

        take_a_pill_button = findViewById(R.id.take_button_id);
        take_a_pill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainMenu.this, Take_a_pill.class));
            }
        });

        add_a_pill_button = findViewById(R.id.add_button_id);
        add_a_pill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainMenu.this, Add_a_pill.class));
            }
        });

        remove_a_pill_button = findViewById((R.id.remove_button_id));
        remove_a_pill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent (MainMenu.this, Remove_a_pill.class));
            }
        });

        exit_button = findViewById(R.id.exit_button_id);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //System.exit(0);
            }
        });

        date = new Date();

        todayText = findViewById(R.id.today_is_textbox);
        String result = new SimpleDateFormat("dd.MM.yyyy").format(date);
        todayText.setText("Today is: \n" + result);

    }
}
