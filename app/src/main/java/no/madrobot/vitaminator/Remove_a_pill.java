package no.madrobot.vitaminator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterRemoveAPill;

import Data.DbHand;
import Model.ListItem;
import Model.Medicine;

public class Remove_a_pill extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_a_pill);

        recyclerView = findViewById(R.id.remove_a_pill_viewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        final DbHand db = new DbHand(this);

        List<Medicine> medicineList = db.getAllMed();
        for (Medicine med: medicineList) {
            String descript = "Doses: " + med.getDoses() + ", Taken: " + med.getStatus();
            ListItem item = new ListItem(med.getName(), descript);
            listItems.add(item);
        }

        adapter = new AdapterRemoveAPill(this, listItems);

        recyclerView.setAdapter(adapter);

    }


}
