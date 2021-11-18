package com.mattkinloch.softwareengineering;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListingsManager {

    // radius in miles
    public static final int RADIUS = 10;

    public static ArrayList<Listing> listings;
    public static ArrayList<Listing> listingsWithinRadius;
    public static ArrayList<Listing> bookings;

    public ListingsManager() {
        listings = new ArrayList<Listing>();
        listingsWithinRadius = new ArrayList<Listing>();
        bookings = new ArrayList<Listing>();
        ListingsThreadTaskJsonURLGet task = new ListingsThreadTaskJsonURLGet(this);
        Log.w("LM", "Start thread");
        task.start();
        Log.w("LM", "Inside constructor, Thread started");
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findListingsWithinRadius();
        findBookings();
    }

    public void updateListingsWithJson( String json ) {
        Log.w( "LM", "Inside updateLocationWithJson" + json);
        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); ++i) {
                //Log.w("ListingsManager", "item: " + jsonArray.getJSONObject(i));
                JSONObject j = jsonArray.getJSONObject(i);
                JSONObject owner = j.getJSONObject("owner");
                String id = j.getString("id");
                String date = j.getString("Date");
                String startTime = j.getString("Start Time");
                String duration = j.getString("Duration");

                String sitterUsername = j.getString("Start Time");

                String ownerName = owner.getString("Name");
                String petName = owner.getString("pet_name");
                String petType = owner.getString("pet_type");
                String petWeight = owner.getString("pet_weight");
                String petBreed = owner.getString("pet_type");
                String description = j.getString("Description");
                String petCareInstructions = owner.getString("pet_care_instructions");
                int booked = j.getInt("Booked");

                String streetAddress = owner.getString("Street");
                String city = owner.getString("City");
                String state = owner.getString("State");
                String zipCode = owner.getString("Zip Code");
                Location loc = new Location(streetAddress, city, state, zipCode);
                Listing list = new Listing(id, date, startTime, duration, ownerName, petName,
                        petType, petBreed, petWeight, petCareInstructions, description, loc,
                        sitterUsername, booked);
                addListing(list);
            }
        } catch( JSONException jsone ) {
            Log.w( "LM", "JSON exception: " + jsone.getMessage() );
        }
    }

    public void findListingsWithinRadius() {
        Location userLocation = MainActivity.userAccount.getLocation();

        for(Listing listing : listings) {
            Location location = listing.getLocation();
            double lonDiff = userLocation.getLon() - location.getLon();
            double latUser = userLocation.getLat();
            double latLoc = location.getLat();
            double distance = Math.sin(degreesToRadians(latUser)) * Math.sin(degreesToRadians(location.getLat()))
                    + Math.cos(degreesToRadians(latUser)) * Math.cos(degreesToRadians(location.getLat())) * Math.cos(degreesToRadians(lonDiff));
            distance = Math.acos(distance);
            distance = radiansToDegrees(distance);
            distance = distance * 60 * 1.1515; // Convert to meters
            distance = distance * 0.8684; // Convert to miles.
            if(distance <= RADIUS) {
                listingsWithinRadius.add(listing);
            }
        }
    }

    public void addBooking(Listing l) {
        bookings.add(l);
        listings.remove(l);
        listingsWithinRadius.remove(l);
        ListingsThreadTaskJsonURLPost task = new ListingsThreadTaskJsonURLPost(l, 0);
        Log.w("ListingsManager", "Start thread");
        task.start();
        Log.w("ListingsManager", "Inside constructor, Thread started");
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void removeBooking(Listing l) {
        bookings.remove(l);
        listings.add(l);
        listingsWithinRadius.add(l);
        ListingsThreadTaskJsonURLPost task = new ListingsThreadTaskJsonURLPost(l, 1);
        Log.w("ListingsManager", "Start thread");
        task.start();
        Log.w("ListingsManager", "Inside constructor, Thread started");
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void findBookings() {
        String username = MainActivity.userAccount.getUsername();
        for(Listing listing : listings) {
            if(username.equals(listing.getSitterUsername())) {
                bookings.add(listing);
            }
        }
    }

    private static double radiansToDegrees(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private static double degreesToRadians(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public void addListing(Listing l) {
        if(l.getBooked() != 1) {
            listings.add(l);
        }
    }

    public ArrayList<Listing> getList() {
        return listings;
    }

    public ArrayList<Listing> getListingsWithinRadius() {
        return listingsWithinRadius;
    }

    public ArrayList<Listing> getBookings() {
        return bookings;
    }

}
