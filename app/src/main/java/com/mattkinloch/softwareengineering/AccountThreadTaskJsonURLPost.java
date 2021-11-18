package com.mattkinloch.softwareengineering;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Make a POST request to create an account OR login
 */
public class AccountThreadTaskJsonURLPost extends Thread {

    /**
     * URL to make a create POST request with
     */
    public static final String URL_CREATE = "http://bmls.cs.loyola.edu/SE-Project-2020-BMLS/web/php/createSitter.php";

    /**
     * URL to make a login POST request with
     */
    public static final String URL_LOGIN = "http://bmls.cs.loyola.edu/SE-Project-2020-BMLS/web/php/loginSitter.php";

    /**
     * The account being created or logged in
     */
    private Account acc;

    /**
     * Flag indicating creation or logging in
     */
    private int flag;

    /**
     * Constructor
     * @param fromAcc: the account being created or logged in
     * @param flag: the flag indicating creation or logging in
     */
    public AccountThreadTaskJsonURLPost(Account fromAcc, int flag) {
        this.acc = fromAcc;
        this.flag = flag;
    }

    /**
     * Run the thread to make either a create or login POST request
     */
    public void run( ) {
        // update View
        Log.w( "Account Thread Post", "Inside run" );
        if(flag == 1) {
            // create account
            try {
                URL url = new URL(URL_CREATE);
                // create a URLConnection
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                // get output stream
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                // (body requires name, username, password, email, phone_number, state, street, city, zip POST
                osw.write("name=" + acc.getName()
                        + "&username=" + acc.getUsername()
                        + "&password=" + acc.getPassword()
                        + "&email=" + acc.getEmail()
                        + "&phone_number=" + acc.getPhoneNumber()
                        + "&state=" + acc.getState()
                        + "&street=" + acc.getStreetAddress()
                        + "&city=" + acc.getCity()
                        + "&zip=" + acc.getZipCode());

                Log.w("Account Thread Post: ", "name=" + acc.getName()
                        + "&username=" + acc.getUsername()
                        + "&password=" + acc.getPassword()
                        + "&email=" + acc.getEmail()
                        + "&phone_number=" + acc.getPhoneNumber()
                        + "&state=" + acc.getState()
                        + "&street=" + acc.getStreetAddress()
                        + "&city=" + acc.getCity()
                        + "&zip=" + acc.getZipCode());
                osw.flush();

                // create an input stream for the URL
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                // read from that input stream
                String line = "";
                String s = "";
                while ((line = br.readLine()) != null) {
                    s += line;
                }
                Log.w("Account Thread Create", "result: " + s);
                acc.updateResponseCodeWithJson(s);
            } catch (Exception e) {
                Log.w("Account Thread Create", "exception: " + e.getMessage());
            }
        }
        else {
            // login
            try {
                URL url = new URL(URL_LOGIN);
                Map<String,String> params = new HashMap<>();
                params.put("username", acc.getUsername());
                params.put("password", acc.getPassword());

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
                Log.w("Account Thread Login", "response: " + s);
                acc.updateLoggedInWithJson(s);

                is.close();
                isr.close();
                br.close();
            } catch (Exception e) {
                Log.w("Account Thread Login", "exception: " + e.getMessage());
            }
        }
    }
}
