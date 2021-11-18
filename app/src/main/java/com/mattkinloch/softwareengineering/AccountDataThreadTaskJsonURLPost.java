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

/**
 * Make a POST request to update the account in the database
 *
 * @author Matt Kinloch
 * @version 1.0
 */
public class AccountDataThreadTaskJsonURLPost extends Thread {

    /**
     * URL to make the POST request to
     */
    public static final String URL_UPDATE = "http://bmls.cs.loyola.edu/SE-Project-2020-BMLS/web/php/updateSitter.php";

    /**
     * The account being to pass in the POST
     */
    private Account acc;

    /**
     * Constructor
     * @param fromAcc: the account to pass in the POST
     */
    public AccountDataThreadTaskJsonURLPost(Account fromAcc) {
        this.acc = fromAcc;
    }

    /**
     * Run the thread making the POST request
     */
    public void run() {
        Log.w("Account Data Thread", "inside run");
        try {
            URL url = new URL(URL_UPDATE);
            Map<String,String> params = new HashMap<>();
            params.put("name", acc.getName());
            params.put("email", acc.getEmail());
            params.put("username", acc.getUsername());
            params.put("phone_number", acc.getPhoneNumber());
            params.put("zip", acc.getZipCode());
            params.put("state", acc.getState());
            params.put("street", acc.getStreetAddress());
            params.put("city", acc.getCity());

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String,String> param : params.entrySet()) {
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
            Log.w("Account Data thread", "response: " + s);
            acc.updateLoggedInWithJson(s);

            is.close();
            isr.close();
            br.close();
        } catch (Exception e) {
            Log.w("Account data thread", "exception: " + e.getMessage());
        }
    }

}
