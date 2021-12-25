    package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class   Sign_Up extends AppCompatActivity  {
    private EditText fname,lname,username,email,phone,pswd;
    private EditText dateBirth;
    private Button butns;
    private FirebaseAuth mAuth;
    private TextView haveAccount;


    DatabaseReference databasereference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        fname = findViewById(R.id.editTextTextPersonName3);
        lname = findViewById(R.id.editTextTextPersonName4);
        username = findViewById(R.id.editTextTextPersonName5);
        email = findViewById(R.id.editTextTextPersonName6);
        phone = findViewById(R.id.editTextPhone);
        pswd = findViewById(R.id.editTextTextPassword2);
        dateBirth = findViewById(R.id.editTextDate);
        butns = findViewById(R.id.buttonj);
        haveAccount = findViewById(R.id.haveAccount);

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sign_Up.this, login.class);
                startActivity(intent);
            }
        });



        butns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname1 = fname.getText().toString();
                String lname1 = lname.getText().toString();
                String username1 = username.getText().toString();
                String emailString = email.getText().toString();
                String phone1 = phone.getText().toString();
                String passwordString = pswd.getText().toString();
                String dateBirth1 = dateBirth.getText().toString();
                if (fname1.isEmpty() || lname1.isEmpty() || username1.isEmpty() || emailString.isEmpty() || phone1.isEmpty() || passwordString.isEmpty() || dateBirth1.isEmpty()) {
                    Toast.makeText(Sign_Up.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }else {
                    databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener()  {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(username1)) {
                                Toast.makeText(Sign_Up.this, "phone is already Exist", Toast.LENGTH_SHORT).show();
                            } else {
                                databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.hasChild(username1)){
                                            Toast.makeText(Sign_Up.this,"phone is already registred",Toast.LENGTH_SHORT).show();
                                        }else {
                                            databasereference.child("users").child(username1).child("fname1").setValue(fname1);
                                            databasereference.child("users").child(username1).child("lname1").setValue(lname1);
                                            databasereference.child("users").child(username1).child("username1").setValue(username1);
                                            databasereference.child("users").child(username1).child("emailString").setValue(emailString);
                                            databasereference.child("users").child(username1).child("phone1").setValue(phone1);
                                            databasereference.child("users").child(username1).child("passwordString").setValue(passwordString);
                                            databasereference.child("users").child(username1).child("datteBirth1").setValue(dateBirth1);

                                            Toast.makeText(Sign_Up.this, "User registred successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                            Intent intent=new Intent(Sign_Up.this, login.class);
                                            startActivity(intent);
                                        }

                                    };

                        @Override
                        public void onCancelled(@NonNull  DatabaseError databaseError) {

                        }




                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull  DatabaseError databaseError) {

                        }

                    });



                }

            }


        }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(Sign_Up.this);
        String username = sessionManagement.getSession();

        if(username.equals("none")){
            //user id logged in and so move to mainActivity

        }
        else{
            startActivity(new Intent(Sign_Up.this, Listegroup.class));

        }
    }

}


















        /*





                String fname1 = fname.getText().toString();
                String lname1 = lname.getText().toString();
                String username1 = username.getText().toString();
                String email1 = email.getText().toString();
                String phone1 = phone.getText().toString();
                String pswd1 = pswd.getText().toString();
                String dateBirth1 = dateBirth.getText().toString();
                if (fname1.isEmpty() || lname1.isEmpty() || username1.isEmpty() || email1.isEmpty() || phone1.isEmpty() || pswd1.isEmpty() || dateBirth1.isEmpty()) {
                    Toast.makeText(Sign_Up.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(phone1)) {
                                Toast.makeText(Sign_Up.this, "phone is already Exist", Toast.LENGTH_SHORT).show();
                            } else {
                                databasereference.child("users").child(phone1).child("fname1").setValue(fname1);
                                databasereference.child("users").child(phone1).child("lname1").setValue(lname1);
                                databasereference.child("users").child(phone1).child("username1").setValue(username1);
                                databasereference.child("users").child(phone1).child("email1").setValue(email1);
                                databasereference.child("users").child(phone1).child("phone1").setValue(phone1);
                                databasereference.child("users").child(phone1).child("pswd1").setValue(pswd1);
                                databasereference.child("users").child(phone1).child("datteBirth1").setValue(dateBirth1);

                                Toast.makeText(Sign_Up.this, "User registred successfully", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                    });


























         */