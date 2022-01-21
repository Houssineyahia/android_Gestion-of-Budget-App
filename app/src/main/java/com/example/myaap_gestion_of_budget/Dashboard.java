package com.example.myaap_gestion_of_budget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity  {
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<MenuClass> liste;
    private ArrayAdapter adapter;
    private static final int RQ_CODE_EDITION = 1;
    DatabaseReference databasereference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    SessionManagement sessionManagement;
    //TextView exprncechanetest = findViewById(R.id.textView5);
    private String  budget = "none";
    ArrayList<String> allbudgets = new ArrayList<String>();
    private Intent intent = getIntent();
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

        //String groupid = intent.getStringExtra("groupid");
        liste = new ArrayList<>();
        liste = initData();
        ListView lv = (ListView) findViewById(android.R.id.list);
        adapter = new MenuListeAdapter(this,liste);
        lv.setAdapter(adapter);

        allbudgets.add("none");
        Spinner spinner = (Spinner) findViewById(R.id.budgetspinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, allbudgets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //////---------------------Get all Budget of group in allbudgets arrayliste ------------------- //////

        databasereference.child("Budget").orderByChild("Group").equalTo(getIntent().getStringExtra("groupid")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allbudgets.clear();
                if(snapshot.exists()){

                    for(DataSnapshot sp : snapshot.getChildren()){
                        //Log.i("22222" , sp.child("Title").getValue().toString());
                        allbudgets.add(sp.child("Title").getValue().toString());

                    }
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "No Budget has found" ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // ---------------------------------------END-----------------------------------------------------------

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
                        handlanychange();
                       // budget = mystr.toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );


        //------------------------add new  Amount --------------------------//

        Button addbdg = findViewById(R.id.addbdg);
        EditText amounth = findViewById(R.id.amounth);
        //String stmush = amounth.getText().toString();
        //int howmush = Integer.valueOf(stmush);

        addbdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(budget.equals("none")){
                    Toast.makeText(getApplicationContext(), "this  has no budget " ,Toast.LENGTH_SHORT).show();

                }else{
                    //Toast.makeText(getApplicationContext(), "this group has no budget " + budget + " " + amounth.getText().toString() ,Toast.LENGTH_SHORT).show();

                  databasereference.child("Budget").orderByChild("Title").equalTo(budget).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int amt = Integer.valueOf(amounth.getText().toString());
                            int oldamt ;
                            String bdgid ="null" ;
                            int some=100;
                            if(snapshot.exists()){
                                for(DataSnapshot sp : snapshot.getChildren()){
                                    bdgid = sp.child("Id").getValue().toString();
                                    oldamt = Integer.valueOf(sp.child("Amount").getValue().toString());
                                    some = oldamt + amt;
                                }
                                databasereference.child("Budget").child(bdgid).child("Amount").setValue(some);
                                handlanychange();
                            }else{

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
        ///////////------------------End here ---------------------------///
    }

    public  void handlanychange(){

        TextView balance = findViewById(R.id.balance);


        databasereference.child("Budget").orderByChild("Title").equalTo(budget).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for(DataSnapshot sp : snapshot.getChildren()){
                        balance.setText(sp.child("Amount").getValue().toString());

                    }

                }else{

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}