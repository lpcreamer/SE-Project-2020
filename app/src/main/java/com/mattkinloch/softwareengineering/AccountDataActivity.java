package com.mattkinloch.softwareengineering;
// This activity is used to edit MainActivity's Account class. The changes here to the Account are reflected in AccountFragment once saved.
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity to edit the data of the Account
 *
 * @author Matt Kinloch
 * @version 1.0
 */
public class AccountDataActivity extends AppCompatActivity {

    /**
     * Create the activity, initializes values of editTexts with current account data
     * @param savedInstanceState: the saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_data);

        TextView usernameTextView = findViewById(R.id.ada_username);
        EditText nameEditText = findViewById(R.id.ada_name);
        EditText emailEditText = findViewById(R.id.ada_email);
        EditText phoneNumberEditText = findViewById(R.id.ada_phone_number);
        EditText streetAddress = findViewById(R.id.ada_street_address);
        EditText city = findViewById(R.id.ada_city);
        EditText state = findViewById(R.id.ada_state);
        EditText zipCode = findViewById(R.id.ada_zip_code);
        EditText passwordEditText = findViewById(R.id.ada_password);
        EditText confirmPasswordEditText = findViewById(R.id.ada_confirm_password);

        usernameTextView.setText(MainActivity.userAccount.getUsername());
        nameEditText.setText(MainActivity.userAccount.getName());
        emailEditText.setText(MainActivity.userAccount.getEmail());
        phoneNumberEditText.setText(MainActivity.userAccount.getPhoneNumber());
        streetAddress.setText(MainActivity.userAccount.getStreetAddress());
        city.setText(MainActivity.userAccount.getCity());
        state.setText(MainActivity.userAccount.getState());
        zipCode.setText(MainActivity.userAccount.getZipCode());
        passwordEditText.setText(MainActivity.userAccount.getProtectedPassword());
        confirmPasswordEditText.setText(MainActivity.userAccount.getProtectedPassword());
    }

    /**
     * Trigger account editing when button is clicked, error check new data, then POST changes
     * @param view: the current View
     */
    public void onClick(View view) {
        //TextView usernameTextView = findViewById(R.id.ada_username);
        EditText nameEditText = findViewById(R.id.ada_name);
        EditText emailEditText = findViewById(R.id.ada_email);
        EditText phoneNumberEditText = findViewById(R.id.ada_phone_number);
        EditText streetAddress = findViewById(R.id.ada_street_address);
        EditText city = findViewById(R.id.ada_city);
        EditText state = findViewById(R.id.ada_state);
        EditText zipCode = findViewById(R.id.ada_zip_code);
        EditText passwordEditText = findViewById(R.id.ada_password);
        EditText confirmPasswordEditText = findViewById(R.id.ada_confirm_password);

        String usernameString = MainActivity.userAccount.getUsername();
        String nameString = nameEditText.getText().toString();
        String emailString = emailEditText.getText().toString();
        String phoneNumberString = phoneNumberEditText.getText().toString();
        String streetAddressString = streetAddress.getText().toString();
        String cityString = city.getText().toString();
        String stateString = state.getText().toString();
        String zipCodeString = zipCode.getText().toString();
        String passwordString = MainActivity.userAccount.getPassword();
        String confirmPasswordString = MainActivity.userAccount.getPassword();

        //usernameTextView.setText(usernameString);

        TextView label = findViewById(R.id.ada_label);

        if(!MainActivity.userAccount.checkEmpty(nameString)
                || !MainActivity.userAccount.checkEmpty(emailString)
                || !MainActivity.userAccount.checkEmpty(phoneNumberString)
                || !MainActivity.userAccount.checkEmpty(streetAddressString)
                || !MainActivity.userAccount.checkEmpty(cityString)
                || !MainActivity.userAccount.checkEmpty(stateString)
                || !MainActivity.userAccount.checkEmpty(zipCodeString)
                || !MainActivity.userAccount.checkEmpty(passwordString)
                || !MainActivity.userAccount.checkEmpty(confirmPasswordString)) {
            Log.w("CAA", "no empty strings");
            // check if email is valid
            if(MainActivity.userAccount.errorCheckEmail(emailString)) {
                // check if pet age is valid
                // check if passwords match
                if (MainActivity.userAccount.passwordMatch(passwordString, confirmPasswordString)) {
                    Log.w("CAA", " " + MainActivity.userAccount.passwordMatch(passwordString, confirmPasswordString));
                    // check if password is valid
                    Log.w("CAA", "validate password: " + passwordString + " result: " + MainActivity.userAccount.errorCheckPassword(passwordString));
                    if (MainActivity.userAccount.errorCheckPassword(passwordString) && MainActivity.userAccount.passwordMatch(passwordString, confirmPasswordString)) {
                        label.setVisibility(View.INVISIBLE);
                        MainActivity.userAccount.updateAccount(nameString,
                                emailString, phoneNumberString, streetAddressString, cityString, stateString,
                                zipCodeString, passwordString);
                        Intent intent = new Intent(this, HomeActivity.class);
                        intent.putExtra("toastId", 1);
                        startActivity(intent);
                    }
                    else {
                        label.setText(R.string.password_invalid);
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
}