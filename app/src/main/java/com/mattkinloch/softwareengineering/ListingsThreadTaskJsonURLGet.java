package com.mattkinloch.softwareengineering;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ListingsThreadTaskJsonURLGet extends Thread{

    public static final String URL = "http://bmls.cs.loyola.edu/SE-Project-2020-BMLS/web/php/getListings.php";

    private ListingsManager lm;

    public ListingsThreadTaskJsonURLGet(ListingsManager fromLm ) {
        lm = fromLm;
    }

    public void run( ) {
        // update View
        Log.w( "Listing GET", "Inside run" );
        HttpURLConnection connection = null;
        try {
            // create a URL
            String getURL = URL;
            URL url = new URL(getURL);
            Log.w( "Listing GET", "after URL" + getURL);
            // create an input stream for the URL
            InputStream is = url.openStream();
            // read from that input stream
            Scanner scan = new Scanner( is );
            String s = "";
            while( scan.hasNext( ) ) {
                s += scan.nextLine( );
                // s is expected to be a JSON string
            }
            lm.updateListingsWithJson(s);
            is.close();
            scan.close();
        } catch( Exception e ) {
            Log.w( "Listing GET", "exception: " + e.getMessage() );
        }
    }

}
