package abanob.prog.awlade.com.cunncry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class StoreViewHoldere extends RecyclerView.Adapter<StoreViewHoldere.ViewHolder> {
    private List<Currancy> currancyList;
    private Context context;


    public StoreViewHoldere(List<Currancy> currancies, Context ctx) {
        currancyList = currancies;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder hotelViewHolder, int position) {
        final Currancy currancy= currancyList.get(position);
         hotelViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
       hotelViewHolder.amount.setText(currancy.getV()+"");
       hotelViewHolder.date.setText(currancy.getDate());
       hotelViewHolder.name.setText(currancy.getTo()+"_"+currancy.getFrom());
    }

    @Override
    public int getItemCount() {
        return currancyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView name,date,amount;
        public ViewHolder(View view) {
            super(view);
            name=(TextView) view.findViewById(R.id.name);
            date=(TextView) view.findViewById(R.id.date);
            amount=(TextView) view.findViewById(R.id.price);

            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){

        }
    }


}