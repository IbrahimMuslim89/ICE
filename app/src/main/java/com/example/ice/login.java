package com.example.ice;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.util.Patterns.EMAIL_ADDRESS;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    Button loginBtn;
    EditText txtemail,txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Log In");

        loginBtn = (Button) findViewById(R.id.loginBtn);
        txtemail = (EditText)findViewById(R.id.Email);
        txtpassword=(EditText)findViewById(R.id.Password);

        mAuth=FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailDb = txtemail.getText().toString().trim();
                String passwordDb = txtpassword.getText().toString().trim();

                if (TextUtils.isEmpty(emailDb))
                {
                    Toast.makeText(login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!EMAIL_ADDRESS.matcher(emailDb).matches())
                {
                    Toast.makeText(login.this, "Please Enter  Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwordDb))
                {
                    Toast.makeText(login.this, "Please Enter  your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordDb.length() < 6)
                {
                    Toast.makeText(login.this, "Too Short Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.signInWithEmailAndPassword(emailDb, passwordDb)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(login.this, "Login Sucessfull ", Toast.LENGTH_SHORT).show();
                                    Intent intent;
                                    intent = new Intent(login.this,Verification.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(login.this, " Login Fail ", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });







            }
        });



    }
}