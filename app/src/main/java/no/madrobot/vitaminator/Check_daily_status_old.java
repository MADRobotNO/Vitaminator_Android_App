package no.madrobot.vitaminator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import Data.DbHand;
import Model.Medicine;

public class Check_daily_status_old extends AppCompatActivity {

    private TextView status_text_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_daily_status_old);
        status_text_view = findViewById(R.id.Status_text_id);



        DbHand db = new DbHand(this);

        List<Medicine> medicineList = db.getAllMed();
        for (Medicine med: medicineList) {
            String taken = "Already taken";
            String not_taken = "Not taken";
            String medicine;
            if (med.getStatus() == 1){
                medicine = med.getName() + ", Status: " + not_taken + "\n";
            }
            else{
                medicine = med.getName() + ", Status: " + taken + "\n";
            }
            status_text_view.append(medicine);
        }



    }
}
