package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity  {
    private ArrayList<MenuClass> liste;
    private ArrayAdapter adapter;
    private static final int RQ_CODE_EDITION = 1;
    //TextView exprncechanetest = findViewById(R.id.textView5);
    private String  budget = "none";

    public ArrayList<MenuClass> initData(){
        Resources res = getResources();
        final String[] libelles =  res.getStringArray(R.array.Menu);
        final String[] prix = res.getStringArray(R.array.prix);
        ArrayList<MenuClass> liste2;
        liste2 = new ArrayList<>();
        for (int i=0; i<libelles.length; ++i) {
            liste2.add(new MenuClass(libelles[i], prix[i] ));
        }

        return liste2;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        liste = new ArrayList<>();
        liste = initData();
        ListView lv = (ListView) findViewById(android.R.id.list);
        adapter = new MenuListeAdapter(this,liste);
        lv.setAdapter(adapter);

        //exprncechanetest.setText(budget);

        Spinner spinner = (Spinner) findViewById(R.id.budgetspinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.budgetliste, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object mystr = parent.getItemAtPosition(position);
                        budget = mystr.toString();
                        Toast.makeText(getApplicationContext(), "Selected Employee: " + budget ,Toast.LENGTH_SHORT).show();
                       // budget = mystr.toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );






        //initiaize and assign variables

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set dashboard select

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        //perform ItemSelectListener

        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:

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
                        startActivity(new Intent(getApplicationContext(), Groups.class));
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }


        });

    }
}