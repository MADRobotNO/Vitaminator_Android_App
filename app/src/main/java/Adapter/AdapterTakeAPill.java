package Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Data.DbHand;
import Model.Medicine;
import no.madrobot.vitaminator.R;

/**
 * Created by Martin Agnar Dahl on 28.01.2018.
 */

public class AdapterTakeAPill extends RecyclerView.Adapter<AdapterTakeAPill.ViewHolder>{

    private Context context;
    private ArrayList<Medicine> listItems;
    private DbHand db;
    private int count;

    public AdapterTakeAPill(Context context, ArrayList<Medicine> listitem){
        this.context = context;
        this.listItems = listitem;
        this.db = new DbHand(context);
    }

    @Override
    public AdapterTakeAPill.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.take_a_pill_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterTakeAPill.ViewHolder holder, int position) {

        Medicine item = listItems.get(position);

        holder.name.setText(item.getName());
        String desc = "Doses: " + item.getDoses()+", Status: "+item.getStatus();
        holder.description.setText(desc);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            layout = itemView.findViewById(R.id.take_pill_lay_id);

            if (listItems.get(count).getStatus()==listItems.get(count).getDoses()){
                itemView.setClickable(false);
                layout.setBackgroundColor(layout.getResources().getColor(R.color.colorPurple));
            }
            count++;

        }

        @Override
        public void onClick(View view) {

            final int position = getAdapterPosition();
            final Medicine item = listItems.get(position);

            final Dialog d = new Dialog(context);
            d.setTitle("NumberPicker");
            d.setContentView(R.layout.dialog_number_picker);
            d.setCancelable(false);
            final Button setBut = (Button) d.findViewById(R.id.setBut);
            final Button cancelBut = (Button) d.findViewById(R.id.cancelBut);
            final NumberPicker numberPicker = (NumberPicker) d.findViewById(R.id.numberPicker);

            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(item.getDoses()-item.getStatus());
            numberPicker.setValue(1);
            numberPicker.setWrapSelectorWheel(false);
            d.show();

            setBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i = numberPicker.getValue();

                    Medicine med = db.getOneMed(item.getName());
                    med.setStatus(med.getStatus()+i);

                    db.updateStatus(med);
                    item.setStatus(med.getStatus());
                    notifyItemChanged(position);

                    String val = "You took " + String.valueOf(i) + " pills";
                    Toast.makeText(context, val, Toast.LENGTH_SHORT).show();
                    d.dismiss();
                }
            });

            cancelBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    d.cancel();
                }
            });

        }
    }
}
