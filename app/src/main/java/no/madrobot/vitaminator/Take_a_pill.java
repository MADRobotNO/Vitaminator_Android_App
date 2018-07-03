package no.madrobot.vitaminator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

import Adapter.AdapterTakeAPill;
import Data.DbHand;

import Model.Medicine;

public class Take_a_pill extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Medicine> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_a_pill);
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        final DbHand db = new DbHand(this);

        ArrayList<Medicine> medicineList = db.getAllMed();
        for (Medicine med: medicineList) {
            listItems.add(med);

        }

        adapter = new AdapterTakeAPill(this, listItems);
        recyclerView.setAdapter(adapter);

    }
}
