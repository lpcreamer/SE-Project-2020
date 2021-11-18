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
 * Activity to create an account
 *
 * @author Matt Kinloch
 * @version 1.0
 */
public class AccountCreateActivity extends AppCompatActivity {

    /**
     * Create the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);
    }

    /**
     * Create a user account by accessing values of edittexts, error checking, and then passing to Account class
     * @param v: the current View
     */
    public void createAccount(View v) {
        EditText username = findViewById(R.id.aac_username);
        EditText name = findViewById(R.id.aac_name);
        EditText email = findViewById(R.id.aac_email);
        EditText phoneNumber = findViewById(R.id.aac_phone_number);
        EditText streetAddress = findViewById(R.id.aac_street_address);
        EditText city = findViewById(R.id.aac_city);
        EditText state = findViewById(R.id.aac_state);
        EditText zipCode = findViewById(R.id.aac_zip_code);
        EditText password = findViewById(R.id.aac_password);
        EditText confirmPassword = findViewById(R.id.aac_confirm_password);

        TextView label = findViewById(R.id.aac_label);

        String usernameString = username.getText().toString();
        String nameString = name.getText().toString();
        String emailString = email.getText().toString();
        String phoneNumberString = phoneNumber.getText().toString();
        String streetAddressString = streetAddress.getText().toString();
        String cityString = city.getText().toString();
        String stateString = state.getText().toString();
        String zipCodeString = zipCode.getText().toString();
        String passwordString = password.getText().toString();
        String confirmPasswordString = confirmPassword.getText().toString();

        Log.w("ACA", "createAccount username: " + usernameString + " name: " + nameString +
                " email: " + emailString + " phoneNumber: " + phoneNumberString + " streetAddress: " + streetAddressString +
                        " city: " + cityString + " state: " + stateString + " zip code: " + zipCodeString + " passwordString: " + passwordString +
                        " confirm password: " + confirmPasswordString);

        Log.w("AACAAC", "onClick");
        // check for any empty fields
        if( !MainActivity.userAccount.checkEmpty(usernameString)
            || !MainActivity.userAccount.checkEmpty(nameString)
            || !MainActivity.userAccount.checkEmpty(emailString)
            || !MainActivity.userAccount.checkEmpty(phoneNumberString)
            || !MainActivity.userAccount.checkEmpty(streetAddressString)
            || !MainActivity.userAccount.checkEmpty(cityString)
            || !MainActivity.userAccount.checkEmpty(stateString)
            || !MainActivity.userAccount.checkEmpty(zipCodeString)
            || !MainActivity.userAccount.checkEmpty(passwordString)
            || !MainActivity.userAccount.checkEmpty(confirmPasswordString)) {
            Log.w("ACA", "no empty strings");
            // check if email is valid
            if(MainActivity.userAccount.errorCheckEmail(emailString)) {
                // check if passwords match
                if (MainActivity.userAccount.passwordMatch(passwordString, confirmPasswordString)) {
                    Log.w("ACA", " " + MainActivity.userAccount.passwordMatch(passwordString, confirmPasswordString));
                    // check if password is valid
                    Log.w("ACA", "validate password: " + passwordString + " result: " + MainActivity.userAccount.errorCheckPassword(passwordString));
                    if (MainActivity.userAccount.errorCheckPassword(passwordString) && MainActivity.userAccount.passwordMatch(passwordString, confirmPasswordString)) {

                        MainActivity.userAccount = new Account(usernameString, nameString, emailString, phoneNumberString, streetAddressString,
                                cityString, stateString, zipCodeString, passwordString);

                        Log.w("ACA", "createAccount 2 username: " + usernameString + " name: " + nameString +
                                " email: " + emailString + " phoneNumber: " + phoneNumberString + " streetAddress: " + streetAddressString +
                                " city: " + cityString + " state: " + stateString + " zip code: " + zipCodeString + " passwordString: " + passwordString +
                                " confirm password: " + confirmPasswordString);

                        if(MainActivity.userAccount.getResponseCode().trim().equals("0")) {
                            label.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            label.setText("Username taken!");
                        }
                    }
                    else {
                        label.setText(R.string.password_invalid);
                        invalidPassword();
                    }
                }
                else {
                    label.setText(R.string.password_mismatch);
                }
            }
            else {
                label.setText(R.string.email_invalid);
            }
        }
        else {
            label.setText(R.string.empty_field);
        }
    }

    /**
     * Display a Toast if the password is invalid
     */
    public void invalidPassword() {
        Toast.makeText(AccountCreateActivity.this, "Passwords must be a minimum of 8 characters, and contain a lowercase (a-z), uppercase (A-Z), number (0-9), and symbol (@#$%^&+=!)",
                Toast.LENGTH_LONG).show();
    }
}