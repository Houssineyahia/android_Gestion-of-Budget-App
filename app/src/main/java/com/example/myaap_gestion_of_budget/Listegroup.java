package com.example.myaap_gestion_of_budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Listegroup extends AppCompatActivity {
    private ArrayList<GroupClass> liste;
    private ArrayAdapter adapter;
    private static final int RQ_CODE_EDITION = 1;
    DatabaseReference databasereference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");


    public ArrayList<GroupClass> initData(){
        Resources res = getResources();
        final String[] libelles =  res.getStringArray(R.array.GNames);
        final String[] Creatore_name = res.getStringArray(R.array.Creators);
        final String[] ids = res.getStringArray(R.array.Groupids);
        ArrayList<GroupClass> liste2;
        liste2 = new ArrayList<>();





        /*for (int i=0; i<libelles.length; ++i) {
            liste2.add(new GroupClass(libelles[i], Creatore_name[i] , ids[i]));
        }*/
        return liste2;

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listegroup);

        liste = new ArrayList<>();
        //ArrayList<GroupClass> SecandL = new  ArrayList<>();
       // liste = initData();
        Log.i("choufhna" , "ok");


        ListView lv = (ListView) findViewById(R.id.mygroups);



        databasereference.child("User_enrolments").child("younssbouch").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot groupid : snapshot.getChildren()) {
                    databasereference.child("Groups").child(groupid.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Log.i("88",String.valueOf(snapshot.child("Group Name").getValue()));
                            Log.i("89",String.valueOf(snapshot.child("Group Admin").getValue()));
                            Log.i("90",String.valueOf(snapshot.getKey().toString()));


                            liste.add(new GroupClass(String.valueOf(snapshot.child("Group Name").getValue()) , String.valueOf(snapshot.child("Group Admin").getValue()) , snapshot.getKey().toString()));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        if(liste.size() != 0){
            adapter = new GroupListeAdapter(this,liste);

            lv.setAdapter(adapter);

        }

        //+ button Click Event
        FloatingActionButton addGroup = findViewById(R.id.floatingActionButton);

        addGroup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "You have chosen the pen: " + " " + String.valueOf(liste.size()), Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getApplicationContext(),Add__.class));

                    }
                }
        );
        ////////////////////
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object o = lv.getItemAtPosition(position);
                        String p = o.toString();
                        Toast.makeText(getApplicationContext(), "You have chosen the pen: " + " " + p, Toast.LENGTH_LONG).show();
                        Intent Action = new Intent(Listegroup.this , GroupActions.class);
                        Action.putExtra("GroupId" , p);
                        startActivity(Action);


                    }
                }
        );






        //initiaize and assign variables

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set dashboard select

        bottomNavigationView.setSelectedItemId(R.id.groups);

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