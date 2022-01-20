package com.example.myaap_gestion_of_budget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.Menu;
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

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity  {
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<MenuClass> liste;
    private ArrayAdapter adapter;
    private static final int RQ_CODE_EDITION = 1;
    SessionManagement sessionManagement;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int item_id=item.getItemId();

        if(item_id==R.id.logout){
            String username = sessionManagement.getSession();


            Intent i = new Intent(Dashboard.this,Start.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);


        }

        if (item_id==R.id.change_password){
            Intent intent=new Intent(Dashboard.this,ChnagePassword.class);
            startActivity(intent);
        }
        return true;
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



        Spinner spinner2 = (Spinner) findViewById(R.id.datePicker);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.datepick, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);



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
                        startActivity(new Intent(getApplicationContext(), Listegroup.class));
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }


        });

    }
}