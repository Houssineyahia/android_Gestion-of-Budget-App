package com.example.myaap_gestion_of_budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Listegroup extends AppCompatActivity implements groupAdapter.groupViewHolder.OnclickListener {
    RecyclerView myRecycle ;
    ArrayList<GroupClass> liste;
    groupAdapter adapter;
    DatabaseReference databasereference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    ProgressDialog progress;
    private static final int RQ_CODE_EDITION = 1;
    Button bt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listegroup);


        myRecycle = findViewById(R.id.recycleview);
        myRecycle.setHasFixedSize(true);
        myRecycle.setLayoutManager(new LinearLayoutManager(this));
        liste = new ArrayList<GroupClass>();
        //liste = initData();

        adapter = new groupAdapter(Listegroup.this , liste , this);
        myRecycle.setAdapter(adapter);

      progress = new ProgressDialog(this);
         progress.setCancelable(false);
         progress.setMessage("Please Wait ..........");
         progress.show();


        
        HandlChangeListener();

        //myRecycle.addOnItemTouchListener( new );


        //+ button Click Event
        FloatingActionButton addGroup = findViewById(R.id.floatingActionButton);

        addGroup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Add__.class));

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


    private  void HandlChangeListener(){
        SessionManagement sessionManagement = new SessionManagement(this);
        String username = sessionManagement.getSession();
        databasereference.child("User_enrolments").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()) {

                    databasereference.child("Groups").child(data.getKey().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.i("44",String.valueOf(snapshot.child("Description").getValue()));
                            liste.add(new GroupClass(String.valueOf(snapshot.child("Group Name").getValue()) , String.valueOf(snapshot.child("Group Admin").getValue()) , data.getKey() , String.valueOf(snapshot.child("Description").getValue())  ));
                            
                            adapter.notifyDataSetChanged();

                            if(progress.isShowing()){
                                progress.dismiss();
                            }
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


    }


    @Override
    public void GroupItemClick(int position) {
        GroupClass selected = liste.get(position);
        //here we cn navigate
         Intent actionInt = new Intent(this , GroupActions.class);
         actionInt.putExtra("GroupId" , selected.getId());
         startActivity(actionInt);

    }
}