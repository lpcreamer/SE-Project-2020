package com.mattkinloch.softwareengineering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Utility for the login layout, sets up page, and controls actions of the login process
 *
 * @author Matt Kinloch
 * @author Scott Guetens
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Create the activity
     *
     * @param savedInstanceState Bundle for AppCompatActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Login function, executed when user clicks the "Login" button
     *
     * @param view the layout with the text fields containing the username and password
     */
    public void login(View view) {
        EditText username = findViewById(R.id.al_username);
        EditText password = findViewById(R.id.al_password);

        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        TextView label = findViewById(R.id.al_label);

        if( !MainActivity.userAccount.checkEmpty(usernameString) ||
            !MainActivity.userAccount.checkEmpty(passwordString)) {
                    MainActivity.userAccount = new Account(usernameString, passwordString);
                    Log.w("LA", "" + MainActivity.userAccount.getLoggedIn());
                    if(MainActivity.userAccount.getLoggedIn()) {
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        label.setText(R.string.network_error);
                    }
        }
        else {
            label.setText(R.string.empty_field);
        }
    }

    /*
    public void forgotPassword(View v) {
        Toast.makeText(LoginActivity.this, "To reset your password, contact support",
                Toast.LENGTH_LONG).show();
    }*/

}