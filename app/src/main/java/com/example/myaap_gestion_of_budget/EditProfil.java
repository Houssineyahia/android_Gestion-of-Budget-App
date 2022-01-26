package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import java.text.BreakIterator;
import java.util.HashMap;

public class EditProfil extends AppCompatActivity {

    public EditText Fname0, Lname0, Uname, Phone0,email0,Fname1,Lname1,Phone1,email1;
    private Button Edit_btn;
    DatabaseReference DBRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        Fname0 = findViewById(R.id.Fname);
        Lname0 = findViewById(R.id.Lname);
        Uname = findViewById(R.id.Uname);
        Phone0 = findViewById(R.id.Phone);
        email0 = findViewById(R.id.Email);
        Edit_btn = findViewById(R.id.Edit_btn);


        Fname1 = findViewById(R.id.Fname);
        Lname1 = findViewById(R.id.Lname);
        Phone1 = findViewById(R.id.Phone);
        email1 = findViewById(R.id.Email);


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

                    Fname0.setText(Fname1_db);
                    Lname0.setText(Lname1_db);
                    Phone0.setText(Phone_db);
                    email0.setText(Email_db);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Edit_btn.setOnClickListener(new View.OnClickListener() {
            int K=0;
            @Override
            public void onClick(View view) {

                String Firstname1 = Fname1.getText().toString();
                String Lastname1 = Lname1.getText().toString();
                String MyPhone1 = Phone1.getText().toString();
                String Myemail1 = email1.getText().toString();





                if (TextUtils.isEmpty(Firstname1) || TextUtils.isEmpty(Lastname1) || TextUtils.isEmpty(MyPhone1) || TextUtils.isEmpty(Myemail1)) {
                    Toast.makeText(getApplicationContext(), "You can't leave the field empty,please fill all fields !", Toast.LENGTH_SHORT).show();

                } else {
                    ++K;

                    DBRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            final String Fname1_db = snapshot.child(username).child("fname1").getValue().toString();
                            final String Lname1_db = snapshot.child(username).child("lname1").getValue().toString();
                            final String Phone_db = snapshot.child(username).child("phone1").getValue().toString();
                            final String Email_db = snapshot.child(username).child("emailString").getValue().toString();

                            DatabaseReference DBRef2 = FirebaseDatabase.getInstance().getReference().child("users").child(username);

                            //snapshot.exists() snapshot.child("username1").getValue().equals(username)
                            if (K == 1){

                            if (!(Fname1_db.equals(Firstname1))) {

                                HashMap MyHashMap = new HashMap();
                                MyHashMap.put("fname1", Firstname1);

                                DBRef2.updateChildren(MyHashMap);


                            }
                            if (!(Lname1_db.equals(Lastname1))) {

                                HashMap MyHashMap1 = new HashMap();
                                MyHashMap1.put("lname1", Lastname1);

                                DBRef2.updateChildren(MyHashMap1);


                            }

                            if (!(Phone_db.equals(MyPhone1))) {

                                HashMap MyHashMap2 = new HashMap();
                                MyHashMap2.put("phone1", MyPhone1);

                                DBRef2.updateChildren(MyHashMap2);


                            }
                            if (!(Email_db.equals(Myemail1))) {

                                HashMap MyHashMap = new HashMap();
                                MyHashMap.put("emailString", Myemail1);

                                DBRef2.updateChildren(MyHashMap);


                            }

                            Toast.makeText(getApplicationContext(), "You did it  !", Toast.LENGTH_SHORT).show();
                            K++;


                        }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    startActivity(new Intent(EditProfil.this, Profil.class));

                }
            }

        });

        /* DatabaseReference DBRef2 = FirebaseDatabase.getInstance().getReference("users");;
        DBRef2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String Email_db = snapshot.child(username).child("emailString").getValue().toString();
                final String Fname1_db = snapshot.child(username).child("fname1").getValue().toString();
                final String Lname1_db = snapshot.child(username).child("lname1").getValue().toString();
                final String Phone_db = snapshot.child(username).child("phone1").getValue().toString();

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
        }); */



//initiaize and assign variables

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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
