package no.madrobot.vitaminator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.AdapterCheckStatus;
import Data.DbHand;
import Model.ListItem;
import Model.Medicine;

public class Check_daily_status extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Medicine> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_daily_status);

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        DbHand db = new DbHand(this);

        ArrayList<Medicine> medicineList = db.getAllMed();
        for (Medicine med: medicineList) {
            String descript = "Taken: " + med.getStatus() + " of " + med.getDoses();
            ListItem item = new ListItem(med.getName(), descript);
            listItems.add(med);
        }

        adapter = new AdapterCheckStatus(this, listItems);
        recyclerView.setAdapter(adapter);



    }
}
