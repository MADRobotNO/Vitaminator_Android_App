package no.madrobot.vitaminator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Date date;

    private TextView todayText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHand db = new DbHand(this);
        DbStartHandler dbStart = new DbStartHandler(this);

        DateTime nowDate = new DateTime();

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


        check_status_button = findViewById(R.id.check_status_button_id);
        check_status_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //Intent intent = new Intent(MainMenu.this, Check_daily_status.class);
                startActivity(new Intent(MainMenu.this, Check_daily_status.class));
            }
        });

        take_a_pill_button = findViewById(R.id.take_button_id);
        take_a_pill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(MainMenu.this, Check_daily_status.class);
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
                System.exit(0);
            }
        });
        date = new Date();

        todayText = findViewById(R.id.today_is_textbox);
        String result = new SimpleDateFormat("dd.MM.yyyy").format(date);
        todayText.setText("Today is: \n" + result);



    }
}
