package com.example.ice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    Toolbar toolbar;
    TextView  Notification;
    TextView Nfc;
    TextView Rescue;
    TextView settings;
    TextView View_profile;
    TextView Location;
    TextView Bloodbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");

        View_profile = (TextView) findViewById(R.id.View_profile);
        Notification = (TextView) findViewById(R.id.Notification);
        Rescue = (TextView) findViewById(R.id.Rescue);
        settings = (TextView) findViewById(R.id.settings);
        Nfc = (TextView) findViewById(R.id.NFC);
        Location = (TextView) findViewById(R.id.Location);
        Bloodbank = (TextView) findViewById(R.id.bloodBank);







        View_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Dashboard.this,View_profile.class);
                startActivity(intent);
            }
        });
        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Dashboard.this,Notification.class);
                startActivity(intent);
            }
        });

        Rescue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Dashboard.this,Rescue.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Dashboard.this,Settings.class);
                startActivity(intent);
            }
        });
        Nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Dashboard.this,NFC.class);
                startActivity(intent);
            }
        });
        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Dashboard.this,Location.class);
                startActivity(intent);
            }
        });
        Bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Dashboard.this,Bloodbank.class);
                startActivity(intent);
            }
        });

    }
}
