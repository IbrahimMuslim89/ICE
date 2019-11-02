package com.example.ice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    //SMS TAG Function Starts
   // private static void tag_sms(){
  //      Log.d(TAG, "HashKey: " + appSignatureHashHelper.getAppSignatures().get(0));
  //  }
    //SMS TAG Function Ends

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.Buttton3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;


                // DASHBOARD REGISTOR BUTTON EDITED FOR FAST LOGIN
                intent = new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this,Hospital_Dashboard.class);
                startActivity(intent);

            }
        });


    }
}
