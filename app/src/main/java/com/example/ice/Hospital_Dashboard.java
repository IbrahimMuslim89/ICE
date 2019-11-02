package com.example.ice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hospital_Dashboard extends AppCompatActivity {

    private Toolbar toolbar;
    TextView Live_location,History,hospital_location,Patient_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__dashboard);
        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");


        Live_location = (TextView) findViewById(R.id.Live_location);
        History = (TextView) findViewById(R.id.History);
        hospital_location = (TextView) findViewById(R.id.Address_h);
        Patient_profile = (TextView) findViewById(R.id.Profile_patient);

        Live_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Hospital_Dashboard.this,Location.class);
                startActivity(intent);
            }
        });
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Hospital_Dashboard.this,Patient_history.class);
                startActivity(intent);
            }
        });
        hospital_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Hospital_Dashboard.this,Hospital_location.class);
                startActivity(intent);
            }
        });

        Patient_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Hospital_Dashboard.this,View_profile.class);
                startActivity(intent);
            }
        });

    }
}
