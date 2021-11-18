package com.mattkinloch.softwareengineering;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_account, R.id.nav_listings, R.id.nav_bookings, R.id.nav_rewards)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // this line is crashing stuff for some reason
        String nameString = MainActivity.userAccount.getName();
        String emailString = MainActivity.userAccount.getEmail();

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.nav_email);

        navUsername.setText(nameString);
        navEmail.setText(emailString);

        int toastId = getIntent().getIntExtra("toastId", 0);
        makeToast(toastId);
    }

    public void makeToast(int toastId) {
        if(toastId == 0) {
            // no toast
        }
        else if(toastId == 1) {
            Toast.makeText(this, "Account successfully updated!", Toast.LENGTH_LONG).show();
            getIntent().removeExtra("toastId");
        }
        else if(toastId == 2) {
            Toast.makeText(this, "Listing successfully added to Bookings!", Toast.LENGTH_LONG).show();
            getIntent().removeExtra("toastId");
        }
        else if(toastId == 3) {
            Toast.makeText(this, "Listing successfully removed from Bookings!", Toast.LENGTH_LONG).show();
            getIntent().removeExtra("toastId");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}