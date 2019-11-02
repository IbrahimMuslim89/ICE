package com.example.ice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Emergency_notification extends AppCompatActivity {

    private Toolbar toolbar;

    TextView Name_en,DOB_en,email_en,health_en,phone_en,Emergency_phone_en,Emergency_name_en,emergency_relation_en,Adress_en,blood_en;



    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_notification);

        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Emergency Location");

        Name_en = (TextView) findViewById(R.id.Namedb);
        DOB_en = (TextView) findViewById(R.id.DOBdb);
        email_en = (TextView) findViewById(R.id.Email_endb);
        health_en = (TextView) findViewById(R.id.Health_endb);
        phone_en = (TextView) findViewById(R.id.Phone_userdb);
        Emergency_phone_en = (TextView) findViewById(R.id.Emergency_userdb);
        Emergency_name_en = (TextView) findViewById(R.id.e_namedb);
        emergency_relation_en =(TextView)  findViewById(R.id.relationdb);
        Adress_en =(TextView)  findViewById(R.id.Address_db);
        blood_en = (TextView) findViewById(R.id.blooddb);


        databaseReference= FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String addres = dataSnapshot.child("addres").getValue().toString();
                String dob = dataSnapshot.child("dob").getValue().toString();
                String econtact = dataSnapshot.child("econtact").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String ename = dataSnapshot.child("ename").getValue().toString();
                String erelation = dataSnapshot.child("erelation").getValue().toString();
                String fname = dataSnapshot.child("fname").getValue().toString();
                String health = dataSnapshot.child("health").getValue().toString();
                String phonenum = dataSnapshot.child("phonenum").getValue().toString();
                String blood_group = dataSnapshot.child("blood_group").getValue().toString();

                Name_en.setText(fname);
                DOB_en.setText(dob);
                email_en.setText(email);
                health_en.setText(health);
                phone_en.setText(phonenum);
                Emergency_phone_en.setText(econtact);
                Emergency_name_en.setText(ename);
                emergency_relation_en.setText(erelation);
                Adress_en.setText(addres);
                blood_en.setText(blood_group);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
