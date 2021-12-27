package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Button buttonSave = findViewById(R.id.button);



        Groupname = findViewById(R.id.editTextTextPersonName4);
        groupdescription = findViewById(R.id.editTextTextPersonName5);
        grouptype=findViewById(R.id.autoCompleteTextView) ;
        String Id = mDatabase.push().getKey();
        SessionManagement sessionManagement=new SessionManagement(Add__.this) ;
        String username= sessionManagement.getSession();


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
                mDatabase.child(Id).child("Group Admin").setValue(username);

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
                        startActivity(new Intent(getApplicationContext(), MainActivit.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(),Profil.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.add:

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