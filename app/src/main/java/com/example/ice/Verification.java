package com.example.ice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Verification extends AppCompatActivity {


    Toolbar toolbar;
    Button verfication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Verification");


        verfication = (Button) findViewById(R.id.Verification_button  );
        verfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Verification.this,Dashboard.class);
                startActivity(intent);
            }
        });








    }






}
