package Adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Medicine;
import no.madrobot.vitaminator.R;


/**
 * Created by Martin Agnar Dahl on 28.01.2018.
 */

public class AdapterCheckStatus extends RecyclerView.Adapter<AdapterCheckStatus.ViewHolder>{

    private Context context;
    private ArrayList<Medicine> listItems;

    public AdapterCheckStatus(Context context, ArrayList<Medicine> listitem){
        this.context = context;
        this.listItems = listitem;
    }

    @Override
    public AdapterCheckStatus.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.take_a_pill_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterCheckStatus.ViewHolder holder, int position) {

        Medicine item = listItems.get(position);

        holder.name.setText(item.getName());
        String desc = "Doses: " + item.getDoses()+", Status: "+item.getStatus();
        holder.description.setText(desc);
        if (listItems.get(position).getStatus()==listItems.get(position).getDoses()){
            holder.layout.setBackgroundColor(holder.layout.getResources().getColor(R.color.colorPurple));
        }
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

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Medicine item = listItems.get(position);
            Toast.makeText(context, "You took " + item.getStatus() +" " + item.getName()
                    + " out of " + item.getDoses(), Toast.LENGTH_SHORT).show();

        }
    }
}
