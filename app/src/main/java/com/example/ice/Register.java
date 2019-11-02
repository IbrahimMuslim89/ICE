package com.example.ice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.graphics.BitmapFactory;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.util.Patterns.EMAIL_ADDRESS;


public class Register extends AppCompatActivity
{
    String diabtedb="";
    String bpdb="";



    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    Calendar myCalendar;
    EditText txfname, txlname, txphonenum,txphonenum2, txhealth, txaddress, txecontact1,txecontact2, txename, txerelation,blood_group;
    EditText DOB,Email,Password,CPassword;

    Button signupBtn;
    Button setButton;
    RadioButton radioDiaY,radioDiaN,radioBPY,radioBPN;

    private static final int image_pick_code=1000;
    private static final int permission_code=1001;

    DatabaseReference databaseReference;


    ImageView imagView5;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Registration");

        txfname=(EditText) findViewById(R.id.F_name);
        txlname=(EditText) findViewById(R.id.L_name);
        Email=(EditText) findViewById(R.id.Email);
        Password=(EditText) findViewById(R.id.Password);
        CPassword=(EditText) findViewById(R.id.CPassword);
        myCalendar = Calendar.getInstance();
        DOB= (EditText) findViewById(R.id.DOB);

        blood_group=(EditText) findViewById(R.id.Blood_group);

        txphonenum=(EditText) findViewById(R.id.phonenum);
        txphonenum2=(EditText) findViewById(R.id.phonenum2);


        txaddress=(EditText) findViewById(R.id.Adress);
        txhealth=(EditText) findViewById(R.id.Health_description);

        txecontact1=(EditText) findViewById(R.id.contacte1);
        txecontact2=(EditText) findViewById(R.id.contacte2);


        txename=(EditText) findViewById(R.id.emergency_person_name);
        txerelation=(EditText) findViewById(R.id.emergency_person_relation);

        radioDiaY=(RadioButton)findViewById(R.id.diabities_y);
        radioDiaN=(RadioButton)findViewById(R.id.diabities_N);
        radioBPY=(RadioButton)findViewById(R.id.bp_y);
        radioBPN=(RadioButton)findViewById(R.id.bp_N);



        signupBtn = (Button) findViewById(R.id.SignupBtn);

        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        mAuth =FirebaseAuth.getInstance();


        signupBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                final String fname       = txfname.getText().toString();
                final String lname       = txlname.getText().toString();
                final String dob         =DOB.getText().toString();
                final String phonenumdb1  = txphonenum.getText().toString();
                final String phonenumdb2  = txphonenum2.getText().toString();


                final String healthdb    = txhealth.getText().toString();
                final String addresdb    = txaddress.getText().toString();
                final String econtactdb1  = txecontact1.getText().toString();
                final String econtactdb2  = txecontact2.getText().toString();

                final String Blooddb  = blood_group.getText().toString();




                final String enamedb     = txename.getText().toString();
                final String erelationdb = txerelation.getText().toString();

                final String email       = Email.getText().toString().trim();
                final String password    = Password.getText().toString().trim();
                final String cpassword   = CPassword .getText().toString().trim();

                if(radioDiaY.isChecked()){
                    diabtedb="YES";
                }
                if(radioDiaN.isChecked()){
                    diabtedb="No";
                }

                if(radioBPY.isChecked()){
                    bpdb="YES";
                }
                if(radioBPN.isChecked()){
                    bpdb="No";
                }



                if(TextUtils.isEmpty(fname)){

                    Toast.makeText(Register.this, "Please Enter Your name", Toast.LENGTH_SHORT).show();


                }

                if(TextUtils.isEmpty(lname)){

                    Toast.makeText(Register.this, "Please Enter your name", Toast.LENGTH_SHORT).show();


                }
                if(TextUtils.isEmpty(dob)){

                    Toast.makeText(Register.this, "Please Enter Age", Toast.LENGTH_SHORT).show();


                }
                if (TextUtils.isEmpty(Blooddb))
                {
                    Toast.makeText(Register.this, "Please Enter Your Blood Group", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phonenumdb2)){

                    Toast.makeText(Register.this, "Please Enter your number", Toast.LENGTH_SHORT).show();


                }
                if(TextUtils.isEmpty(addresdb)){

                    Toast.makeText(Register.this, "Please Enter adress", Toast.LENGTH_SHORT).show();


                }
                if(TextUtils.isEmpty(enamedb)){

                    Toast.makeText(Register.this, "Please Enter name", Toast.LENGTH_SHORT).show();


                }
                if(TextUtils.isEmpty(econtactdb2)){

                    Toast.makeText(Register.this, "Please Enter  Emergence Conatct", Toast.LENGTH_SHORT).show();


                }
                if(TextUtils.isEmpty(erelationdb)){

                    Toast.makeText(Register.this, "Please Enter  Emergence holder relation", Toast.LENGTH_SHORT).show();


                }


                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Register.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!EMAIL_ADDRESS.matcher(email).matches())
                    {
                    Toast.makeText(Register.this, "Please Enter  Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                     }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Register.this, "Please Enter  your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(cpassword))
                {

                    Toast.makeText(Register.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6)
                {
                    Toast.makeText(Register.this, "Too Short Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.equals(cpassword))
                 {

                    Toast.makeText(Register.this, "first verification passed", Toast.LENGTH_SHORT).show();

                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener( Register.this,new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task <AuthResult> task)
                                {

                                    if (task.isSuccessful()){

                                        User information = new User(
                                                fname,lname,dob,Blooddb,email,password,cpassword,phonenumdb1,phonenumdb2,healthdb,
                                                addresdb,econtactdb1,econtactdb2,enamedb,erelationdb,diabtedb,bpdb
                                        ) ;

                                        FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                //startActivity(new Intent(getApplicationContext(), Verification.class));
                                                Toast.makeText(Register.this, "Registartion Complete ", Toast.LENGTH_SHORT).show();
                                                Intent intent;
                                                intent = new Intent(Register.this,Verification.class);
                                                startActivity(intent);


                                            }
                                        });

                                        //startActivity(new Intent(getApplicationContext(), Verification.class));
                                         Toast.makeText(Register.this, "Authentication Sucessfull ", Toast.LENGTH_SHORT).show();
                                        Intent intent;
                                        intent = new Intent(Register.this,Verification.class);
                                        startActivity(intent);
                                    }
                                    else
                                           {

                                            Toast.makeText(Register.this, "Authentication failed ", Toast.LENGTH_SHORT).show();
                                              //startActivity(new Intent(getApplicationContext(), Verification.class));
                                           }

                                }
                            });
                 }
            }
        });


////////////////////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////////////////


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        DOB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Register.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        setButton = (Button) findViewById(R.id.Set_button);
        imagView5 = (ImageView) findViewById(R.id.imageView5);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){

                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission,permission_code);
                    }
                    else{
                        pickImagefromGallery();

                    }
                }
                else{
                    pickImagefromGallery();

                }

            }
        });

    }

    private void pickImagefromGallery() {

        Intent intent =  new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,image_pick_code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {




        switch (requestCode){
            case permission_code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImagefromGallery();
                }
                else
                {
                    Toast.makeText(this, "Permission Denied!....", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (resultCode == RESULT_OK && requestCode == image_pick_code)
        {
            imagView5.setImageURI(data.getData());
        }
    }



    private void updateLabel()
    {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DOB.setText(sdf.format(myCalendar.getTime()));
    }
}