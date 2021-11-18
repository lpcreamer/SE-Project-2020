package com.mattkinloch.softwareengineering;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListingsRecyclerAdapter extends RecyclerView.Adapter<ListingsRecyclerAdapter.MyViewHolder>{

    private Context context;
    private final ArrayList<Listing> listingArrayList;

    public ListingsRecyclerAdapter(ArrayList<Listing> listingArrayList, Context context) {
        this.context = context;
        this.listingArrayList = listingArrayList;
        for(Listing l : MainActivity.listingsManager.getList()) {
            Log.w("LRA", "listing: " + l.toString());
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView petName;
        public Button infoButton;
        public MyViewHolder(final View view) {
            super(view);
            petName = view.findViewById(R.id.li_pet_name);
            infoButton = view.findViewById(R.id.li_info_button);
        }
    }

    @NonNull
    @Override
    public ListingsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListingsRecyclerAdapter.MyViewHolder holder, final int position) {
        String petNameString = listingArrayList.get(position).getPetName();
        holder.petName.setText(petNameString);
        holder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListingActivity.class);
                intent.putExtra("LISTING_ID", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listingArrayList.size();
    }
}