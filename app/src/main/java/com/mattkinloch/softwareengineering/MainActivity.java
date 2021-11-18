package com.mattkinloch.softwareengineering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The Utility class for the apps main load in page
 *
 * @author Matt Kinloch
 * @author Scott Guetens
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The user's logged in account
     */
    public static Account userAccount;

    /**
     * The internal cache of the listings from the databse
     */
    public static ListingsManager listingsManager;

    /**
     * Activity load in function. Get the listings form the databse and any logged in users)
     *
     * @param savedInstanceState the bundle for the AppCompatActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.userAccount = new Account();
        listingsManager = new ListingsManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Redirect to login page if internal credentials are not found.
     *
     * @param view The activities layout page
     */
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Redirect to the create account page
     *
     * @param view The activities layout page
     */
    public void createAccount(View view) {
        Intent intent = new Intent(this, AccountCreateActivity.class);
        startActivity(intent);
    }
}