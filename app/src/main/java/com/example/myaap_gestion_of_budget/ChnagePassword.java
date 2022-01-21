package com.example.myaap_gestion_of_budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.example.myaap_gestion_of_budget.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ChnagePassword extends AppCompatActivity {
    EditText newpass,confirmnewpass,oldpass;
    Button confirm;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chnage_password);
        newpass=findViewById(R.id.editTextTextnewpassword1);


        confirm.setOnClickListener(new View.OnClickListener() {
                                       @Override
                          public void onClick(View v) {
                                           String pass=newpass.getText().toString();
                                           HashMap hashMap=new HashMap();
                                           hashMap.put("passwordString",pass);
                                          databasereference.child("users").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                              @Override
                                              public void onSuccess(@NonNull  Object o) {

                                              }
                                          });


                                       }
                                   }
            );
        }
    }
