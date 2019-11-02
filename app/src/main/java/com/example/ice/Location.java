package com.example.ice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Location extends AppCompatActivity {

    private Toolbar toolbar;
    ToggleButton toggleButton_location;

    private static final String TAG= "Location";
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Location Settings");
        toggleButton_location = (ToggleButton) findViewById(R.id.toggleButton);

    //Map Implementation
        if (isServiceOK()){
            init();

        }

    }
    private void init(){
        Button btnMap = (Button) findViewById(R.id.CurrentLocation);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Location.this,MapActivity.class);
                startActivity(intent);
            }
        });


    }





    public boolean isServiceOK() {
        Log.d(TAG, "is ServicesOK : Checking Google Services Version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Location.this);

        if (available == ConnectionResult.SUCCESS) {
            //everythign si fine, user can make requires
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            // an resolveable error occured
            Log.d(TAG,"isServicesOK: an resolveable error occured");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(Location.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "Cant make map Request", Toast.LENGTH_SHORT).show();
        }
        return false;


    }





}

