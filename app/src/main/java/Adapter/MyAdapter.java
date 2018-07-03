package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Model.ListItem;
import no.madrobot.vitaminator.R;

/**
 * Created by Martin Agnar Dahl on 28.01.2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context context;
    private List<ListItem> listItems;

    public MyAdapter(Context context, List listitem){
        this.context = context;
        this.listItems = listitem;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.take_a_pill_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

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

        }

        @Override
        public void onClick(View view) {
            //Get pos. row taped
            int position = getAdapterPosition();
            ListItem item = listItems.get(position);

            Toast.makeText(context, item.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
