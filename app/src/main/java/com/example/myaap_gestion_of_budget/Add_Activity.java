package com.example.myaap_gestion_of_budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Add_Activity extends AppCompatActivity {
    String[] items={"Food","transport","cofee","others"};
    private static final String FILE_NAME="example.txt";
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    EditText txt1;
    EditText dropdwon;
    Button btn2;
    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);
        EditText  txt1= findViewById(R.id.editTextTextPersonName3);
        btn2=findViewById(R.id.add_activity);
        dropdwon=findViewById(R.id.autoCompleteTextView5);

        SessionManagement sessionManagement=new SessionManagement(Add_Activity.this);
        autoCompleteTextView=findViewById(R.id.autoCompleteTextView5);
        adapterItems=new ArrayAdapter<String>(this,R.layout.dropdwon_item,items);
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
                        String t=getIntent().getStringExtra("Budgetid");
                        mDatabase.child(Id).child("idbudget").setValue(t);

                    }
                });




            }

        });
        }
    }