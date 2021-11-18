package com.mattkinloch.softwareengineering;

import android.util.Log;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Make a GET request to receive account information on login
 */
public class AccountThreadTaskJsonURLGet extends Thread{

    /**
     * URL to make the GET request to
     */
    public static final String URL_GET_ACCOUNT = "http://bmls.cs.loyola.edu/SE-Project-2020-BMLS/web/php/getSitter.php?";

    /**
     * The account being updated with GET request response
     */
    private Account acc;

    /**
     * Constructor
     * @param fromAcc: the account being updated with the GET request response
     */
    public AccountThreadTaskJsonURLGet(Account fromAcc) {
        this.acc = fromAcc;
    }

    /**
     * Run the thread making the GET request
     */
    public void run() {
        // now make request to get rest of info from DB
        Log.w( "Account Thread Get", "Inside run" );
        try {
            Log.w("Account thread Get", "username: " + acc.getUsername());
            String getUrl = URL_GET_ACCOUNT + "&username=" + acc.getUsername();
            URL url = new URL(getUrl);
            // create an input stream for the URL
            InputStream is = url.openStream();
            // read from that input stream
            Scanner scan = new Scanner(is);
            String s = "";
            while (scan.hasNext()) {
                s += scan.nextLine();
                // s is expected to be a JSON string
            }
            Log.w("Account Thread Login", "response: " + s);
            acc.updateAccountWithJson(s);
            is.close();
            scan.close();
        } catch (Exception e) {
            Log.w("Account Thread Login", "exception: " + e.getMessage());
        }

    }

}
