package com.example.myaap_gestion_of_budget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Profil extends AppCompatActivity {

    private Button toedit;
    public TextView Full_name ,Fname, Lname, Uname, Phone,Email,Age;


    DatabaseReference DBRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        SessionManagement sessionManagement=new SessionManagement(Profil.this) ;
        String username = sessionManagement.getSession();
        Button chnpass = (Button) findViewById(R.id.changepass);
        toedit = findViewById(R.id.toedit);


        Full_name = findViewById(R.id.Full_name);
        Fname = findViewById(R.id.Fname);
        Lname = findViewById(R.id.Lname);
        Uname = findViewById(R.id.Uname);

        Phone = findViewById(R.id.Phone);

        Email = findViewById(R.id.Email);
        Age = findViewById(R.id.Age);






        DBRef = FirebaseDatabase.getInstance().getReference("users");



       DBRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String Email_db = snapshot.child(username).child("emailString").getValue().toString();
                final String Fname1_db = snapshot.child(username).child("fname1").getValue().toString();
                final String Lname1_db = snapshot.child(username).child("lname1").getValue().toString();
                final String Phone_db = snapshot.child(username).child("phone1").getValue().toString();

                 String Age_db = snapshot.child(username).child("datteBirth1").getValue().toString();




                if (snapshot.exists() ) {

                    Full_name.setText(Fname1_db +" "+Lname1_db);
                    Uname.setText(username);
                    Fname.setText(Fname1_db);
                    Lname.setText(Lname1_db);
                    Phone.setText(Phone_db);
                    Email.setText(Email_db);
                    Age.setText(Age_db);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        toedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity =  new Intent(getApplicationContext(),  EditProfil.class);
                startActivity(otherActivity);

            }});

        chnpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity =  new Intent(getApplicationContext(),  Edit_Passwd.class);
                startActivity(otherActivity);
            }
        });






        //initiaize and assign variables

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set dashboard select

        bottomNavigationView.setSelectedItemId(R.id.profil);

        //perform ItemSelectListener

        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){


                    case R.id.profil:
                        return true;

                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(),Add__.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.groups:
                        startActivity(new Intent(getApplicationContext(),Listegroup.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }


        });
    }
}
