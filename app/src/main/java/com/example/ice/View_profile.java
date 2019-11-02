package com.example.ice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class View_profile extends AppCompatActivity {
    private Toolbar toolbar;

    TextView Name_p,DOB_p,email_p,health_p,phone_p,Emergency_phone_p,Emergency_name_p,emergency_relation_p,Adress_p,blood_p;



    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        toolbar =  findViewById(R.id.toolbar);

        Name_p = (TextView) findViewById(R.id.Name_p);
        DOB_p = (TextView) findViewById(R.id.DOB_p);
        email_p = (TextView) findViewById(R.id.Email_p);
        health_p = (TextView) findViewById(R.id.Health_p);
        phone_p = (TextView) findViewById(R.id.Phone_p);
        Emergency_phone_p = (TextView) findViewById(R.id.Emergency_p);
        Emergency_name_p = (TextView) findViewById(R.id.Emergency_name_p);
        emergency_relation_p =(TextView)  findViewById(R.id.Emergency_relation_p);
        Adress_p =(TextView)  findViewById(R.id.Adress_p);
        blood_p = (TextView) findViewById(R.id.Blood_p);

        toolbar.setTitle("Profile");

        //databaseReference = FirebaseDatabase.getInstance().getReference("User").child("6Jy0RBEDaJZE25vP51vfpo30Aqn2");

        databaseReference=FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
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

                Name_p.setText(fname);
                DOB_p.setText(dob);
                email_p.setText(email);
                health_p.setText(health);
                phone_p.setText(phonenum);
                Emergency_phone_p.setText(econtact);
                Emergency_name_p.setText(ename);
                emergency_relation_p.setText(erelation);
                Adress_p.setText(addres);
                blood_p.setText(blood_group);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}