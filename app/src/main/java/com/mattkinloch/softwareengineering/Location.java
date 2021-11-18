package com.mattkinloch.softwareengineering;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The location of a user in the system. The location is used to get a geo object to identify a range of
 * listings  in their area when searching for available appointments to book.
 *
 * @author Matt Kinloch
 * @author Scott Guetens
 * @version 1.0
 */
public class Location {

    /**
     * The physical street address of a location, number, address, apt
     */
    protected String streetAddress;

    /**
     * The city of the location
     */
    protected String city;

    /**
     * The state of the location
     */
    protected String state;

    /**
     * The 5 digit zip code of the location
     */
    protected String zipCode;

    /**
     * A generated latitude value
     */
    protected double lat;

    /**
     * A generated longitude value
     */
    protected double lon;

    /**
     * Constructor for a Location object,
     *
     * @param streetAddress street address of location, with number, street, apt.
     * @param city the city of the Location object
     * @param state the state of the location
     * @param zipCode the zip code of the object
     */
    public Location(String streetAddress, String city,
                    String state, String zipCode){
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        // don't run with empty parameters
        if(!streetAddress.equals("")) {
            LocationThreadTaskJsonUrl task = new LocationThreadTaskJsonUrl(this);
            Log.w("Location", "Start thread");
            task.start();
            Log.w("Location", "Inside constructor, Thread started");
            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update the {@value this#lat} and {@value this#lon} values from a json
     * payload
     *
     * @param json the json payload containing "lat" and "lon" payloads
     */
    public void updateLocationWithJson( String json ) {
        Log.w( "Location", "Inside updateLocationWithJson" + json);
        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject1 = jsonArray.getJSONObject( 0 );
            lat = Double.parseDouble(jsonObject1.getString( "lat" ));
            lon = Double.parseDouble(jsonObject1.getString( "lon" ));
        } catch( JSONException jsone ) {
            Log.w( "Location", "inside updateLocationWithJSon - JSON exception: " + jsone.getMessage() );
        }
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
