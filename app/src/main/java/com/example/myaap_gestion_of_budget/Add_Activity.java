package com.example.myaap_gestion_of_budget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Add_Activity extends AppCompatActivity {
    String[] items={"Food","transport","cofee","others"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);
        autoCompleteTextView=findViewById(R.id.autoCompleteTextView5);
        adapterItems=new ArrayAdapter<String>(this,R.layout.dropdwon_item,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item =parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"item"+item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}