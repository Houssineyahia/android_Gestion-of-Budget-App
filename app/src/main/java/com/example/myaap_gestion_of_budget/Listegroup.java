package com.example.myaap_gestion_of_budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Listegroup extends AppCompatActivity {
    private ArrayList<GroupClass> liste;
    private ArrayAdapter adapter;
    private static final int RQ_CODE_EDITION = 1;

    public ArrayList<GroupClass> initData(){
        Resources res = getResources();
        final String[] libelles =  res.getStringArray(R.array.GNames);
        final String[] prix = res.getStringArray(R.array.Creators);
        ArrayList<GroupClass> liste2;
        liste2 = new ArrayList<>();
        for (int i=0; i<libelles.length; ++i) {
            liste2.add(new GroupClass(libelles[i], prix[i]));
        }

        return liste2;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listegroup);

        liste = new ArrayList<>();
        liste = initData();
        ListView lv = (ListView) findViewById(R.id.mygroups);
        adapter = new GroupListeAdapter(this,liste);
        lv.setAdapter(adapter);


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
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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