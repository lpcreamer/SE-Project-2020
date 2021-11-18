package com.mattkinloch.softwareengineering;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * An class representing a sitter account
 * @author Matt Kinloch
 * @version 1.0
 */
public class Account {

    /**
     * The username of the account
     */
    protected String username;

    /**
     * The name of the account owner
     */
    protected String name;

    /**
     * The email of the account owner
     */
    protected String email;

    /**
     * The phone number of the account owner
     */
    protected String phoneNumber;

    /**
     * The street address of the account owner
     */
    protected String streetAddress;

    /**
     * The city the account owner lives in
     */
    protected String city;

    /**
     * The state the account owner lives in
     */
    protected String state;

    /**
     * The zip code the account owner lives in
     */
    protected String zipCode;

    /**
     * The password associated with the account
     */
    protected String password;

    /**
     * Whether or not the account has logged in
     */
    private boolean loggedIn;

    /**
     * The response code when logging in
     */
    private String responseCode;

    /**
     * A Location object holding the account's location information
     */
    protected Location loc;

    /**
     * Default Constructor
     */
    public Account() {
        this.username = "";
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
        this.streetAddress = "";
        this.city = "";
        this.state="";
        this.zipCode="";
        this.password = "";
        this.responseCode = "2";
        loc = new Location(streetAddress, city, state, zipCode);
    }

    /**
     * Login Constructor
     * @param username: the username trying to be logged in with
     * @param password: the password trying to be logged in with
     */
    public Account(String username, String password) {
        this.username = username;
        Log.w("Account", "constructor, username: " + this.username);
        this.password = password;
        this.responseCode = "0";
        // access database here probably, filler values for now
        AccountThreadTaskJsonURLPost task = new AccountThreadTaskJsonURLPost(this, 0);
        Log.w("Account", "Start post thread");
        task.start();
        Log.w("Account", "Inside constructor, Thread started");
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AccountThreadTaskJsonURLGet task2 = new AccountThreadTaskJsonURLGet(this);
        Log.w("Account", "Start get thread");
        task2.start();
        Log.w("Account", "Inside constructor, Thread started");
        try {
            task2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //loc = new Location(streetAddress, city, state, zipCode);
    }

    /**
     * Create Account Constructor
     * @param username: the username associated with the account being created
     * @param name: the name associated with the account being created
     * @param email: the email associated with the account being created
     * @param phoneNumber: the phone number associated with the account being created
     * @param streetAddress: the street address associated with the account being created
     * @param city: the city associated with the account
     * @param state: the state associated with the account
     * @param zipCode: the zip code associated with the account
     * @param password: the password associated with the account
     */
    public Account(String username, String name, String email, String phoneNumber, String streetAddress,
                   String city, String state, String zipCode, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.password = password;
        this.loggedIn = true;
        loc = new Location(streetAddress, city, state, zipCode);
        AccountThreadTaskJsonURLPost task = new AccountThreadTaskJsonURLPost(this, 1);
        Log.w("Account", "Start thread");
        task.start();
        Log.w("Account", "Inside constructor, Thread started");
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update account information after logging in
     * @param s: the response from the login POST request to parse
     */
    public void updateLoggedInWithJson(String s) {
        Log.w("Account", "updateLoggedInWithJson: " + s);
        int i = Integer.parseInt(s);
        if(i == 0) {
            Log.w("Account", "set to true 1" + this.loggedIn);
            this.loggedIn = true;
            Log.w("Account", "set to true 2" + this.loggedIn);
        }
        else {
            Log.w("Account", "set to false");
            this.loggedIn = false;
        }
        Log.w("Account", "end of update method" + this.loggedIn);
    }

    /**
     * Update the response code received from a POST request
     * @param s: the response code
     */
    public void updateResponseCodeWithJson(String s) {
        Log.w("Account", "updateResponseCodeWithJson: " + s);
        String code = s.trim();
        if(code.equals("0")) {
            Log.w("Account", "before set to 0" + this.responseCode);
           this.responseCode = code;
            Log.w("Account", "after set to 0" + this.loggedIn);
        }
        else {
            Log.w("Account", "set to not 0");
            this.responseCode = code;
        }
        Log.w("Account", "end of update method" + this.loggedIn);
    }

    /**
     * Update the account information after account is edited
     * @param s: the response to parse to update the account
     */
    public void updateAccountWithJson(String s) {
        try {
            JSONObject j = new JSONObject(s);
            this.name = j.getString("Name");
            this.email = j.getString("Email");
            this.phoneNumber = j.getString("Phone Number");
            this.streetAddress = j.getString("Street");
            this.city = j.getString("City");
            this.state = j.getString("State");
            this.zipCode = j.getString("Zip Code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the account information
     * @param name: the new name associated with the account
     * @param email: the new email associated with the account
     * @param phoneNumber: the new phone number associated with the account
     * @param streetAddress: the new street address associated with the account
     * @param city: the new city associated with the account
     * @param state: the new state associated with the account
     * @param zipCode: the new zip code associated with the account
     * @param password: the new password associated with the account
     */
    public void updateAccount(String name, String email, String phoneNumber, String streetAddress,
                              String city, String state, String zipCode, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.password = password;
        AccountDataThreadTaskJsonURLPost task = new AccountDataThreadTaskJsonURLPost(this);
        Log.w("Account", "Start data post thread");
        task.start();
        Log.w("Account", "Inside constructor, Thread started");
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if a string is empty
     * @param s: the string to check
     * @return true if empty, false if not
     */
    public boolean checkEmpty(String s) {
        return s.trim().length() == 0;
    }

    /**
     * Check if an email contains @
     * @param email: the email to check
     * @return true if the email contains @, false if not
     */
    public boolean errorCheckEmail(String email) {
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }

    /**
     * Check if a password contains: one uppercase(A-Z), one lowercase(a-z), one number(0-9), and one symbol (@#$%^&+=!)
     * @param password: the password to check
     * @return true if the password contains necessary characters, false if not
     */
    public boolean errorCheckPassword(String password) {
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}";
        return password.matches(regex);
    }

    /**
     * Check if two passwords are equal to each other
     * @param s1: the first password
     * @param s2: the second password
     * @return true if equal, false if not
     */
    public boolean passwordMatch(String s1, String s2) {
        return s1.equals(s2);
    }

    /**
     * Protect the password by converting it to *'s
     * @return String: the password as *'s
     */
    public String getProtectedPassword() {
        Log.w("Account", "getProtectedPassword");
        String s = "";
        for(int i = 0; i < password.length(); i++) {
            s+="*";
        }
        return s;
    }

    public String getUsername() { return username; }

    public String getName() { return name; }

    public String getEmail(){ return email; }

    public String getPhoneNumber(){ return phoneNumber; }

    public String getStreetAddress() { return streetAddress; }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getZipCode() { return zipCode; }

    public String getPassword(){ return password; }

    public boolean getLoggedIn() { return this.loggedIn; }

    public Location getLocation() { return loc; }

    public String getResponseCode() { return responseCode; }

}
