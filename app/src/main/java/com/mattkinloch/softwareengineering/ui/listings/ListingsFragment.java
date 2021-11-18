package com.mattkinloch.softwareengineering.ui.listings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mattkinloch.softwareengineering.MainActivity;
import com.mattkinloch.softwareengineering.ListingsRecyclerAdapter;
import com.mattkinloch.softwareengineering.Listing;
import com.mattkinloch.softwareengineering.R;

import java.util.ArrayList;

public class ListingsFragment extends Fragment /* implements View.OnClickListener*/{

    private ArrayList<Listing> listingArrayList;
    private RecyclerView recyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_listings, container, false);
        recyclerview = root.findViewById(R.id.listings_recycler_view);
        listingArrayList = MainActivity.listingsManager.getListingsWithinRadius();
        setAdapter();
        return root;
    }

    private void setAdapter() {
        ListingsRecyclerAdapter adapter = new ListingsRecyclerAdapter(listingArrayList, this.getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
    }

}