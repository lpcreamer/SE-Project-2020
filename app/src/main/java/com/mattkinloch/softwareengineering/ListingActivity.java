package com.mattkinloch.softwareengineering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * A utility page for a Listing layout.
 * @author Matt Kinloch
 * @author Scott Guetens
 * @version 1.0
 */
public class ListingActivity extends AppCompatActivity {

    String position;

    /**
     * On load function for this activity
     *
     * @param savedInstanceState Bundle for the AppCompatActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        position = "" + getIntent().getIntExtra("LISTING_ID", 0);
        displayInfo();
    }

    /**
     * Display the information to the listing layout from the listing object in the database
     */
    public void displayInfo() {
        Listing l = MainActivity.listingsManager.getListingsWithinRadius().get(Integer.parseInt(position));
        TextView t1 = findViewById(R.id.listing_pet_name);
        String petName = "Pet Name: " + l.getPetName();
        t1.setText(petName);
        TextView t2 = findViewById(R.id.listing_pet_type);
        String petType = "Pet Type: " + l.getPetType();
        t2.setText(petType);
        TextView t3 = findViewById(R.id.listing_pet_breed);
        String petBreed = "Pet Breed: " + l.getPetBreed();
        t3.setText(petBreed);
        TextView t4 = findViewById(R.id.listing_pet_weight);
        String petWeight = "Pet Weight: " + l.getPetWeight();
        t4.setText(petWeight);
        TextView t5 = findViewById(R.id.listing_owner_name);
        String ownerName = "Owner Name: " + l.getOwnerName();
        t5.setText(ownerName);
        TextView t6 = findViewById(R.id.listing_description);
        String desc = "Description: " + l.getDescription();
        t6.setText(desc);
        TextView t7 = findViewById(R.id.listing_pet_care_instructions);
        String petCareInstructions = "Pet Care Instructions: " + l.getPetCareInstructions();
        t7.setText(petCareInstructions);
        TextView t8 = findViewById(R.id.listing_date);
        String date = "Date: " + l.getDate();
        t8.setText(date);
        TextView t9 = findViewById(R.id.listing_start_time);
        String startTime = "Time: " + l.getStartTime();
        t9.setText(startTime);
        TextView t10 = findViewById(R.id.listing_duration);
        String duration = "Duration: " + l.getDuration();
        t10.setText(duration);
    }

    /**
     * A utility function to accept a listing and move it to the bookings layout.
     *
     * @param view the booking view page
     */
    public void onClick(View view) {
        Listing l = MainActivity.listingsManager.getListingsWithinRadius().get(Integer.parseInt(position));
        MainActivity.listingsManager.addBooking(l);

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("toastId", 2);
        startActivity(intent);

    }
}