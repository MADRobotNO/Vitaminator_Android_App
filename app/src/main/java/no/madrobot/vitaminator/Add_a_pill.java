package no.madrobot.vitaminator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Data.DbHand;
import Model.Medicine;

public class Add_a_pill extends AppCompatActivity {

    private Button add_button;
    private Button cancel_button;
    private EditText pill_name;
    private EditText pill_dose;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        final DbHand db = new DbHand(this);
        setContentView(R.layout.activity_add_a_pill);

        pill_name = findViewById(R.id.pill_name_editText);

        pill_dose = findViewById(R.id.pill_dose_editText);

        add_button = findViewById(R.id.add_pill_but);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check_if_text_ok = 0;
                int check_if_dose_ok = 0;
                if (TextUtils.isEmpty(pill_name.getText().toString())) {
                    pill_name.setError("Enter pill name");
                }
                else{
                    check_if_text_ok=1;
                }

                if (TextUtils.isEmpty(pill_dose.getText().toString())){
                    pill_dose.setError("Enter dose");
                }
                else{
                    if (Integer.parseInt(pill_dose.getText().toString())==0){
                        pill_dose.setError("Has to be greater than 0");
                    }
                    else {
                        check_if_dose_ok=1;
                    }
                }

                if (check_if_text_ok==1 && check_if_dose_ok==1){
                    Medicine medicine = new Medicine(pill_name.getText().toString(), Integer.parseInt(pill_dose.getText().toString()), 0);
                    if (db.addPill(medicine)){
                        Toast.makeText(context, pill_name.getText().toString() + " added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }
        });

        cancel_button = findViewById(R.id.cancel_pill_but);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
