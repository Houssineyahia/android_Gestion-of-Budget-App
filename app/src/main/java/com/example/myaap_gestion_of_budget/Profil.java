package com.example.myaap_gestion_of_budget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
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
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Profil extends AppCompatActivity {

    private Button toedit;
    public TextView Full_name ,Fname, Lname, Uname, Phone,Email,Age;
    SessionManagement sessionManagement;


    DatabaseReference DBRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    //------------------menu top------------------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int item_id=item.getItemId();
        sessionManagement=new SessionManagement(Profil.this) ;
        if(item_id==R.id.logout){
            sessionManagement.removeSession();
            Intent i = new Intent(Profil.this,Start.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();

        }

        if (item_id==R.id.change_password){
            Intent intent=new Intent(Profil.this,Edit_Passwd.class);
            startActivity(intent);
        }
        return true;
    }
    //--------------------------end here----------------------------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
         sessionManagement=new SessionManagement(Profil.this) ;
        String username = sessionManagement.getSession();

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



                /* String input = Age_db;
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
                LocalDate localDate = LocalDate.parse( input , formatter );
                int User_Year = localDate.getYear();
                String adate = String.valueOf(User_Year);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date MyAge1 = null;
                try {
                    MyAge1 = sdf.parse(Age_db);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int TheY = MyAge1.getYear();
                /* DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/mm/yyyy" ) ;
                LocalDate ld = LocalDate.parse( Age_db , f ) ;
                int Y_user =ld.getYear();
                LocalDate localDate = LocalDate.now();
                int year_syst = localDate.getYear();
                int User_Age = year_syst - Y_user;
                      */


               /* int BDateToInt =Integer.parseInt(Age_db);;
                int Y_user = BDateToInt.getYear() ;
                Date d  =new Date();
                int year_syst = d.getYear();
                int User_Age = year_syst- Y_user;
                   System.out.println(date.getYear());  // 2012
*/

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
