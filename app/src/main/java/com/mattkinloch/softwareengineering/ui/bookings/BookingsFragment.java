package com.mattkinloch.softwareengineering.ui.bookings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mattkinloch.softwareengineering.BookingsRecyclerAdapter;
import com.mattkinloch.softwareengineering.Listing;
import com.mattkinloch.softwareengineering.ListingsRecyclerAdapter;
import com.mattkinloch.softwareengineering.MainActivity;
import com.mattkinloch.softwareengineering.R;

import java.util.ArrayList;

public class BookingsFragment extends Fragment {

    private ArrayList<Listing> listingArrayList;
    private RecyclerView recyclerview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bookings, container, false);
        recyclerview = root.findViewById(R.id.bookings_recycler_view);
        listingArrayList = MainActivity.listingsManager.getBookings();
        setAdapter();
        return root;
    }

    private void setAdapter() {
        BookingsRecyclerAdapter adapter = new BookingsRecyclerAdapter(listingArrayList, this.getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
    }
}