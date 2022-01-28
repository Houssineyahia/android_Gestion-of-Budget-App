package com.example.myaap_gestion_of_budget;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class addBudget extends AppCompatActivity {
    //variables

    EditText title;
    EditText end_Date;
    EditText start_Date;
    EditText Amount;
    TextView groupname;
        // connection with database
    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    private  Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);
        groupname = findViewById(R.id.namegroup);

        groupname.setText(getIntent().getStringExtra("groupname"));
        //groupname.setText("groupname");

        mDatabase = FirebaseDatabase.getInstance().getReference("Budget");
        Button add__budget = findViewById(R.id.add__Budget);


        title = findViewById(R.id.title);
        end_Date = findViewById(R.id.endDate);
        start_Date=findViewById(R.id.startDate) ;
        Amount=findViewById(R.id.Amount) ;


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);



        String Id = mDatabase.push().getKey();
        SessionManagement sessionManagement=new SessionManagement(addBudget.this) ;


        add__budget.setOnClickListener(v -> {

            String titleD = title.getText().toString();
            String startDate = start_Date.getText().toString();
            String endDate = end_Date.getText().toString();
            String amount = Amount.getText().toString();




            if (titleD.isEmpty() || endDate.isEmpty() || startDate.isEmpty() || amount.isEmpty()) {
                Toast.makeText(addBudget.this, "Please fill the fields ", Toast.LENGTH_SHORT).show();


            }else {

                mDatabase.child(Id).child("Id").setValue(Id);
                mDatabase.child(Id).child("Group").setValue(getIntent().getStringExtra("groupid"));
                mDatabase.child(Id).child("Amount").setValue(amount);
                mDatabase.child(Id).child("Start date").setValue(startDate);
                mDatabase.child(Id).child("End date").setValue(endDate);
                mDatabase.child(Id).child("Title").setValue(titleD);

                Toast.makeText(addBudget.this, "Budget id added !", Toast.LENGTH_SHORT).show();
                //groupe id
               // String tt=getIntent().getStringExtra("groupid");

                //Budget id
                // clear all fields
                title.getText().clear();
                start_Date.getText().clear();
                end_Date.getText().clear();
                Amount.getText().clear();

                Intent actiongroupe = new Intent(this , Dashboard.class);
                actiongroupe.putExtra("groupid" ,getIntent().getStringExtra("groupid"));
                startActivity(actiongroupe);



            }
        });

                // calendar start day
                start_Date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(addBudget.this,  new DatePickerDialog.OnDateSetListener(){


                    @Override
                    public void onDateSet(DatePicker view , int year, int month, int day) {
                        month=month+1;
                        String date = day+"-"+month+"-"+year;
                        start_Date.setText(date);
                    }
                },year,month,day);
                        datePickerDialog.show();


                // calendar end day
                end_Date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(addBudget.this,  new DatePickerDialog.OnDateSetListener(){


                            @Override
                            public void onDateSet(DatePicker view , int year, int month, int day) {
                                month=month+1;
                                String date = day+"-"+month+"-"+year;
                                end_Date.setText(date);
                            }
                        },year,month,day);
                        datePickerDialog.show();

                    }});

            }});



        //initiaize and assign variables

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set dashboard select

        bottomNavigationView.setSelectedItemId(R.id.add);

        //perform ItemSelectListener

        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){


                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(),Profil.class));
                        overridePendingTransition(0,0);
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


            });}
}





