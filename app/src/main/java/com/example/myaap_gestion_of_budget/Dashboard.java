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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Dashboard extends AppCompatActivity  {
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<MenuClass> liste = new ArrayList<>();
    private ArrayAdapter adapter;
    private static final int RQ_CODE_EDITION = 1;
    DatabaseReference databasereference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    SessionManagement sessionManagement;
    //TextView exprncechanetest = findViewById(R.id.textView5);
    private String  budget = "none";
    private String idbudget = "none";
    ArrayList<String> allbudgets = new ArrayList<String>();
    private Intent intent = getIntent();


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

                        databasereference.child("Budget").orderByChild("Title").equalTo(budget).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    for(DataSnapshot sp : snapshot.getChildren()){
                                        idbudget = sp.child("Id").getValue().toString();
                                        //Toast.makeText(getApplicationContext(), "Selected Employee: " + idbudget ,Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });
                        Toast.makeText(getApplicationContext(), "first " + budget ,Toast.LENGTH_SHORT).show();
                        handlanychange();
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

        //------------floating btn to add activity -------------------//
        FloatingActionButton addactivity = findViewById(R.id.toAddactivity);

        addactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Selected Employee: " + idbudget  ,Toast.LENGTH_SHORT).show();
                Intent actionInt = new Intent(Dashboard.this , Add_Activity.class);
                actionInt.putExtra("Budgetid" , idbudget);
                actionInt.putExtra("idgroupe",getIntent().getStringExtra("groupid"));
                startActivity(actionInt);
            }
        });
    }



    public  void handlanychange(){

        TextView balance = findViewById(R.id.balance);
        TextView transaction = findViewById(R.id.transaction);

        databasereference.child("Budget").orderByChild("Title").equalTo(budget).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for(DataSnapshot sp : snapshot.getChildren()){
                        balance.setText(sp.child("Amount").getValue().toString() + " DH");
                    }
                }else{
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        if(!budget.equals("none")){
            liste.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "The Id: " + budget  ,Toast.LENGTH_SHORT).show();



            databasereference.child("Budget").orderByChild("Title").equalTo(budget).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot sp : snapshot.getChildren()){
                            idbudget = sp.child("Id").getValue().toString();


                            databasereference.child("Activity").orderByChild("idbudget").equalTo(idbudget).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if(snapshot.exists()){
                                        Log.i("22",snapshot.getValue().toString());
                                        int some = 0;
                                        for (DataSnapshot data: snapshot.getChildren()) {
                                            liste.add(new MenuClass(data.child("activity").getValue().toString(),"- " + data.child("price").getValue().toString() + " DH" , data.child("date").getValue().toString() ,data.child("user").getValue().toString() , data.child("comment").getValue().toString()));
                                            some = some + Integer.valueOf(data.child("price").getValue().toString());

                                        }
                                        transaction.setText("- " + String.valueOf(some));
                                        Collections.reverse(liste);
                                        Log.i("22",String.valueOf(liste.size()));
                                        adapter.notifyDataSetChanged();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }



                    }else{

                        balance.setText("0");
                        transaction.setText("0");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });

        }else{
            liste.clear();
            adapter.notifyDataSetChanged();
            balance.setText("0");
            transaction.setText("0");
        }


    }
}