package com.example.myaap_gestion_of_budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Add_Activity extends AppCompatActivity {
    String[] items={"Food","transport","cofee","others"};
    private static final String FILE_NAME="example.txt";
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    EditText txt1;
    EditText dropdwon;
    EditText comment;
    Spinner spinner1;
    private DatePickerDialog datePickerDialog;
    Button btn2;
    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    DatabaseReference spinnerRef;
    ArrayList<String> spinnerList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);
        EditText txt1 = findViewById(R.id.editTextTextPersonName3);
        btn2 = findViewById(R.id.add_activity);
        comment=findViewById(R.id.comment);
        dropdwon = findViewById(R.id.autoCompleteTextView5);

       //spinner
        spinner1=findViewById(R.id.spinner);



        //spinenr


         spinnerList=new ArrayList<>();
         adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,spinnerList);
         //spinner




        SessionManagement sessionManagement = new SessionManagement(Add_Activity.this);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView5);
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdwon_item, items);
        autoCompleteTextView.setAdapter(adapterItems);
        mDatabase = FirebaseDatabase.getInstance().getReference("Activity");
        String Id = mDatabase.push().getKey();



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase.child(Id).child("ID").setValue(Id);
                        mDatabase.child(Id).child("activity").setValue(item);
                        mDatabase.child(Id).child("price").setValue(txt1.getText().toString());
                        String t = getIntent().getStringExtra("Budgetid");
                        mDatabase.child(Id).child("idbudget").setValue(t);
                        String f=getTodaysDate();
                        mDatabase.child(Id).child("date").setValue(f);
                        mDatabase.child(Id).child("comment").setValue(comment.getText().toString());
                        mDatabase.child(Id).child("user").setValue(spinner1.getSelectedItem().toString());
                        //spinner

                        Toast.makeText(Add_Activity.this, "Done ! ", Toast.LENGTH_SHORT).show();



                    }
                });


            }

        });
    }
    @Override
    protected void onStart()
    {
        // TODO Auto-generated method stub
        super.onStart();

        String idg=getIntent().getStringExtra("idgroupe");

       spinnerRef=FirebaseDatabase.getInstance().getReference("group_enrolments");
        spinnerRef.child(idg).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.i("44",snapshot.getChildren().toString());
                spinnerList.clear();
                for (DataSnapshot data: snapshot.getChildren()) {
                   // Log.i("22" , data.getKey());
                    spinnerList.add(data.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //spinnerList.add(item.getValue().toString().substring(1, item.getValue().toString().indexOf('=')));
        adapter.notifyDataSetChanged();
        spinner1.setAdapter(adapter);

    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year)
    {
        return month + " " + day + " " + year;
    }






}


/*



for(DataSnapshot item:snapshot.getChildren()){
                   if(item.getKey().equals("-MrwOd-0-Vx92tk0qRWw")) {
                       //item.getValue().toString().substring(1,item.getValue().toString().indexOf('='))
                       list.add(item.getValue().toString().substring(1, item.getValue().toString().indexOf('=')));

                   }
                  //  }
                   continue;
                }
                spinnerList.addAll(list);
                adapter.notifyDataSetChanged();
            }
 */