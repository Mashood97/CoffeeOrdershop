package com.example.masho.coffeeordershop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Masho on 10/5/2018.
 */

public class RecyclerMyPostAdapter extends RecyclerView.Adapter<RecyclerMyPostAdapter.MyViewHolder> {

    //in this we are creating reciept adapter so that we can generate reciept.
    Context context;
    ArrayList<ModelMyOrders> profiles;

    public RecyclerMyPostAdapter(Context c , ArrayList<ModelMyOrders> p)
    {
        context = c;
        profiles = p;
    }


    // inflate the custom layout of reciept list item to show on the activity context we pass. reciept_list_item is custom layout.++
    @Override
    public RecyclerMyPostAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_myposts,parent,false));
    }

    //same as food item adpter
    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.username.setText(profiles.get(position).getName());
        holder.price.setText(profiles.get(position).getPrice());
        holder.whippedcream.setText(profiles.get(position).getWhippedCream());
        holder.chocolatecream.setText(String.valueOf(profiles.get(position).getChocolateCream()));

    }


    @Override
    public int getItemCount() {
        return profiles.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView username,price,whippedcream,chocolatecream;

        public MyViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.UserNames);
            price = itemView.findViewById(R.id.Price);
            whippedcream = itemView.findViewById(R.id.whippedCream);
            chocolatecream = itemView.findViewById(R.id.chocolateCream);
        }
    }
}
