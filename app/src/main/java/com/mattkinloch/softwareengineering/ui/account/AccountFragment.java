package com.mattkinloch.softwareengineering.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mattkinloch.softwareengineering.AccountDataActivity;
import com.mattkinloch.softwareengineering.MainActivity;
import com.mattkinloch.softwareengineering.R;

/**
 * Fragment of the Navigation Drawer displaying account information
 */
public class AccountFragment extends Fragment implements View.OnClickListener {

    /**
     * Create the fragment
     * @param inflater: the layout being inflated
     * @param container: the container of the layout
     * @param savedInstanceState: the saved state
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);
        Button b = root.findViewById(R.id.fa_edit_button);
        b.setOnClickListener(this);

        TextView usernameTextView = root.findViewById(R.id.fa_username);
        TextView lastNameTextView = root.findViewById(R.id.fa_name);
        TextView emailTextView = root.findViewById(R.id.fa_email_address);
        TextView phoneNumberTextView = root.findViewById(R.id.fa_phone_number);
        TextView streetAddressTextView = root.findViewById(R.id.fa_street_address);
        TextView cityTextView = root.findViewById(R.id.fa_city);
        TextView stateTextView = root.findViewById(R.id.fa_state);
        TextView zipCodeTextView = root.findViewById(R.id.fa_zip_code);
        TextView passwordTextView = root.findViewById(R.id.fa_password);

        usernameTextView.setText(MainActivity.userAccount.getUsername());
        lastNameTextView.setText(MainActivity.userAccount.getName());
        emailTextView.setText(MainActivity.userAccount.getEmail());
        phoneNumberTextView.setText(MainActivity.userAccount.getPhoneNumber());
        streetAddressTextView.setText(MainActivity.userAccount.getStreetAddress());
        cityTextView.setText(MainActivity.userAccount.getCity());
        stateTextView.setText(MainActivity.userAccount.getState());
        zipCodeTextView.setText(MainActivity.userAccount.getZipCode());
        passwordTextView.setText(MainActivity.userAccount.getProtectedPassword());
        return root;
    }

    /**
     * Change activity on button click
     * @param v: the current View
     */
    @Override
    public void onClick(View v) {
        Log.w("AF", "onClick");
        switch (v.getId()) {
            case R.id.fa_edit_button:
                Intent intent = new Intent(getActivity(), AccountDataActivity.class);
                startActivity(intent);
                break;
        }
    }
}