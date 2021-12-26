package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add__ extends AppCompatActivity {

    EditText Groupname;
    EditText groupdescription;
    EditText grouptype;
    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mDatabase = FirebaseDatabase.getInstance().getReference("Groups");

        Button buttonSave = findViewById(R.id.button);



        Groupname = findViewById(R.id.editTextTextPersonName4);
        groupdescription = findViewById(R.id.editTextTextPersonName5);
        grouptype=findViewById(R.id.autoCompleteTextView) ;
        String Id = mDatabase.push().getKey();
        


        buttonSave.setOnClickListener(v -> {


            String groupn = Groupname.getText().toString();
            String des =  groupdescription.getText().toString();
            String gtype = grouptype.getText().toString();

            if (groupn.isEmpty() || des.isEmpty() || gtype.isEmpty()) {
                Toast.makeText(Add__.this, "Please fill the fields ", Toast.LENGTH_SHORT).show();
            } else {

                mDatabase.child(Id).child("Id").setValue(Id);
                mDatabase.child(Id).child("Group Name").setValue(groupn);
                mDatabase.child(Id).child("Description").setValue(des);
                mDatabase.child(Id).child("Group Type").setValue(gtype);


                Toast.makeText(Add__.this, "Group created !", Toast.LENGTH_SHORT).show();

                // clear fields
                Groupname.getText().clear();
                groupdescription.getText().clear();
                grouptype.getText().clear();

            }
        });
        //initiaize and assign variables

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set dashboard select

        bottomNavigationView.setSelectedItemId(R.id.add);

        //perform ItemSelectListener

        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(),Profil.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.add:

                        return true;

                    case R.id.groups:
                        startActivity(new Intent(getApplicationContext(), Groups.class));
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }


        });
    }


}