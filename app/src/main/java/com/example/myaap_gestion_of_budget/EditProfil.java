package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfil extends AppCompatActivity {

    public EditText Fname, Lname, Uname, Phone,email;
    private Button Edit_btn;
    DatabaseReference DBRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        Fname = findViewById(R.id.Fname);
        Lname = findViewById(R.id.Lname);
        Uname = findViewById(R.id.Uname);
        Phone = findViewById(R.id.Phone);
        email = findViewById(R.id.Email);
        Edit_btn = findViewById(R.id.Edit_btn);



        SessionManagement sessionManagement=new SessionManagement(EditProfil.this) ;
        String username = sessionManagement.getSession();
        Uname.setText(username);






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

                     Uname.setText(username);
                    Fname.setText(Fname1_db);
                    Lname.setText(Lname1_db);
                    Phone.setText(Phone_db);
                    email.setText(Email_db);
                    //Age.setText(String.valueOf());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });











        Edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstname = Fname.getText().toString();
                String Lastname = Lname.getText().toString();
                String MyPhone = Phone.getText().toString();
                String Myemail = email.getText().toString();

                DBRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        if (snapshot.child("username1").getValue().equals(username) ) {


                    }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });






    }
}










          /* BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set dashboard select

        bottomNavigationView.setSelectedItemId(R.id.profil);

        //perform ItemSelectListener

        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;

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
*/
