package com.mattkinloch.softwareengineering;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class ListingsThreadTaskJsonURLPost extends Thread {

    public static final String URL_ACCEPT = "http://bmls.cs.loyola.edu/SE-Project-2020-BMLS/web/php/acceptListing.php";
    public static final String URL_UNACCEPT = "http://bmls.cs.loyola.edu/SE-Project-2020-BMLS/web/php/unacceptListing.php";
    private Listing listing;
    // if flag = 0, accept. if flag = 1, unaccept.
    private int flag;

    public ListingsThreadTaskJsonURLPost(Listing fromListing, int flag) {
        listing = fromListing;
        this.flag = flag;
    }

    public void run( ) {
        Log.w("LTT Accept", "inside run");
        if(flag == 0) {
            try {
                URL url = new URL(URL_ACCEPT);
                Map<String, String> params = new HashMap<>();
                params.put("id", listing.getId());
                params.put("username", MainActivity.userAccount.getUsername());
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> param : params.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append('&');
                    }
                    sb.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    sb.append('=');
                    sb.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = sb.toString().getBytes("UTF-8");

                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.getOutputStream().write(postDataBytes);

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                String s = "";
                while ((line = br.readLine()) != null) {
                    s += line;
                }
                Log.w("LTT Accept", "response: " + s);

                is.close();
                isr.close();
                br.close();
            } catch (Exception e) {
                Log.w("LTT Accept", "exception: " + e.getMessage());
            }
        }
        else {
            try {
                URL url = new URL(URL_UNACCEPT);
                Map<String, String> params = new HashMap<>();
                params.put("id", listing.getId());
                params.put("username", MainActivity.userAccount.getUsername());
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> param : params.entrySet()) {
                    if (sb.length() != 0) {
                        sb.append('&');
                    }
                    sb.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    sb.append('=');
                    sb.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = sb.toString().getBytes("UTF-8");

                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.getOutputStream().write(postDataBytes);

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                String s = "";
                while ((line = br.readLine()) != null) {
                    s += line;
                }
                Log.w("LTT Cancel", "response: " + s);

                is.close();
                isr.close();
                br.close();
            } catch (Exception e) {
                Log.w("Cancel", "exception: " + e.getMessage());
            }
        }
    }

}
