package Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Data.DbHand;
import Model.ListItem;
import Model.Medicine;
import no.madrobot.vitaminator.R;

/**
 * Created by Martin Agnar Dahl on 21.06.2018.
 */
public class AdapterRemoveAPill extends RecyclerView.Adapter<AdapterRemoveAPill.ViewHolder> {

    private Context context;
    private List<ListItem> listItems;
    private DbHand db;

    public AdapterRemoveAPill(Context context, List listItem) {
        this.context = context;
        this.listItems = listItem;
        this.db = new DbHand(context);
    }

    @Override
    public AdapterRemoveAPill.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.remove_a_pill_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRemoveAPill.ViewHolder holder, int position) {

        ListItem item = listItems.get(position);

        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            //notifyDataSetChanged();
        }

        @Override
        public void onClick(View view) {
            Log.d("OnClick", "List clicked");
            int position = getAdapterPosition();
            final ListItem item = listItems.get(position);

            final Dialog d = new Dialog(context);
            d.setTitle("AreYouSure?");
            d.setContentView(R.layout.dialog_are_you_sure);
            d.setCancelable(false);
            final Button yesBut = (Button) d.findViewById(R.id.button_Yes_id);
            final Button noBut = (Button) d.findViewById(R.id.button_no_id);
            noBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    d.cancel();
                }
            });
            yesBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Medicine med = db.getOneMed(item.getName());
                    db.deletePill(med);
                    String val = "You removed " + item.getName();
                    Toast.makeText(context, val, Toast.LENGTH_SHORT).show();

                    listItems.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    d.dismiss();
                }
            });
            //notifyDataSetChanged();
            d.show();
        }
    }
}
