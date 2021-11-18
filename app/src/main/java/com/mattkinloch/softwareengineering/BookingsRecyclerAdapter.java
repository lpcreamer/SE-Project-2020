package com.mattkinloch.softwareengineering;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * A recycler adabpter for the bookings list view to display all the users accepted bookings
 * in a table
 *
 * @author Matt Kinloch
 * @author Scott Guetens
 * @version 1.0
 */
public class BookingsRecyclerAdapter extends RecyclerView.Adapter<BookingsRecyclerAdapter.MyViewHolder> {

    /**
     * The context to use for the recycler tables layout
     */
    private Context context;

    /**
     * The list of {@value Listing} objects to display in the view
     */
    private final ArrayList<Listing> listingArrayList;

    /**
     * Constructor for the recycler table, set up the bookings from the databse and the context
     * @param listingArrayList the list of bookings from the database
     * @param context the context for the recycler view to use
     */
    public BookingsRecyclerAdapter(ArrayList<Listing> listingArrayList, Context context) {
        this.context = context;
        this.listingArrayList = listingArrayList;
        for(Listing l : MainActivity.listingsManager.getList()) {
            Log.w("BRA", "listing: " + l.toString());
        }
    }

    /**
     * The view holders for one instance of a Listing in the recycler view
     *
     * @author Matt Kinloch
     * @author Scott Guetens
     * @author 1.0
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        /**
         * The text view for the pet's name in the listing
         */
        public TextView petName;

        /**
         * A button to display more information about the listing
         */
        public Button infoButton;

        /**
         * Contstructor to assign a listing to a MyViewHolder object
         *
         * @param view the view for displaying this
         */
        public MyViewHolder(final View view) {
            super(view);
            petName = view.findViewById(R.id.bi_pet_name);
            infoButton = view.findViewById(R.id.bi_info_button);
        }
    }

    /**
     * Utility function to call when making a individual cell in the recycler view, sets up a listing
     * with a MyViewHolder
     *
     * @param parent the view group of the recycler adaptor
     * @param viewType the view type
     * @return a ViewHolder object
     */
    @NonNull
    @Override
    public BookingsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_items, parent, false);
        return new BookingsRecyclerAdapter.MyViewHolder(itemView);
    }

    /**
     * Bind the view holder to the position in the adaptor
     *
     * @param holder the view holder object MyViewHolder
     * @param position the index of the cell
     */
    @Override
    public void onBindViewHolder(@NonNull BookingsRecyclerAdapter.MyViewHolder holder, final int position) {
        String petNameString = listingArrayList.get(position).getPetName();
        holder.petName.setText(petNameString);
        holder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookingActivity.class);
                intent.putExtra("LISTING_ID", position);
                context.startActivity(intent);
            }
        });
    }

    /**
     * Get the number of items to display in the recycler view
     *
     * @return {@value this#listingArrayList}'s size
     */
    @Override
    public int getItemCount() {
        return listingArrayList.size();
    }

}
