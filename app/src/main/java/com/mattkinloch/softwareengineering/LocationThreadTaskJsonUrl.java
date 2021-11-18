package com.mattkinloch.softwareengineering;

import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * A Utility class to get the location geo values based on the location IQ api
 * and physical address values
 *
 * @author Matt Kinloch
 * @author Scott Guetens
 * @version 1.0
 */
public class LocationThreadTaskJsonUrl extends Thread {

    /**
     * The URL of the location IQ api
     */
    public static final String URL = "https://us1.locationiq.com/v1/search.php";

    /**
     * Project API key for location IQ
     */
    public static final String key = "pk.b9c69d09841bc378c7955f07c3035fff";

    /**
     * The location object to get a geo for
     */
    private Location loc;

    /**
     * Set the location object of the thread

     * @param fromLoc location objects
     */
    public LocationThreadTaskJsonUrl(Location fromLoc ) {
        loc = fromLoc;
    }

    /**
     * Thread run function (runs on execute)
     */
    public void run( ) {
        // update View
        Log.w( "Location Thread", "Inside run" );

        try {
            // create a URL
            String getURL = URL + "?key=" + key + "&street=" + loc.streetAddress + "&city=" + loc.city + "&state=" + loc.state + "&postalcode=" + loc.zipCode + "&format=json";
            Log.w("Location Thread", "URL: " + getURL);
            URL url = new URL(getURL);
            // create an input stream for the URL
            InputStream is = url.openStream();
            // read from that input stream
            Scanner scan = new Scanner( is );
            String s = "";
            while( scan.hasNext( ) ) {
                s += scan.nextLine( );
                // s is expected to be a JSON string
            }
            loc.updateLocationWithJson(s);
            is.close();
            scan.close();
        } catch( Exception e ) {
            Log.w( "Location", "exception: " + e.getMessage() );
        }
    }
}

