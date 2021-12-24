package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    //private EditText email1, pswd;
   // private Button Connect_Btn;
   // private FirebaseAuth mAuth;
    //private TextView join;

    private EditText inputEmail, inputPassword;
    private Button btnSignup;
    private FirebaseAuth mAuth;
    private TextView btnLogin;
    DatabaseReference databasereference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //email1 = findViewById(R.id.username);
        //pswd = findViewById(R.id.passwd);
        // Connect_Btn = findViewById(R.id.Connect_Btn);
        // join=findViewById(R.id.joinus);
        // mAuth = FirebaseAuth.getInstance();

        //

        inputEmail = findViewById(R.id.username);
        inputPassword = findViewById(R.id.passwd);
        btnSignup = findViewById(R.id.Connect_Btn);
        btnLogin = findViewById(R.id.joinus);
        mAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, Sign_Up.class));
            }
        });

     /*   btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
        */


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userString = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(userString) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();

                } else {
                    databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(userString)) {
                                final String getpassword = snapshot.child(userString).child("passwordString").getValue(String.class);
                                if (getpassword.equals(password)) {
                                    Toast.makeText(login.this, "good", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this, Dashboard.class));

                                } else {
                                    Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(login.this, "wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }}


               //progressBar.setVisibility(View.VISIBLE);

                /*/authenticate user
                mAuth.signInWithEmailAndPassword(emailString,passwordString)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    Toast.makeText(login.this,"error",Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}





        /*

        Connect_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);

            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,Sign_Up.class);
                startActivity(intent);
            }
        });
        Connect_Btn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               String emailString = email1.getText().toString();
                                               String passwordString = pswd.getText().toString();
                                               if (TextUtils.isEmpty(emailString)) {
                                                   email1.setError("Email is required");
                                               }
                                               if (TextUtils.isEmpty(passwordString)) {
                                                   pswd.setError("passwd is required");
                                               } else {

                                                   mAuth.signInWithEmailAndPassword(emailString, passwordString)
                                                           .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                                               @Override
                                                               public void onComplete(@NonNull Task<AuthResult> task) {
                                                                   if (!task.isSuccessful()) {
                                                                       // there was an error
                                                                       Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
                                                                   } else {
                                                                       Intent intent = new Intent(login.this, MainActivity.class);
                                                                       startActivity(intent);
                                                                       finish();
                                                                   }
                                                               }
                                                           });
                                               }
                                           });
                                       }
    }








/*
                    databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(emailString)){
                                 String getPassword=dataSnapshot.child(emailString).child(passwordString).getValue(String.class);
                                if(getPassword.equals(passwordString)){
                                    Toast.makeText(login.this,"success",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this,MainActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(login.this,"wrong password",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(login.this,"wrong",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull  DatabaseError databaseError) {

                        }
                    });

                }
            }
        });*/


