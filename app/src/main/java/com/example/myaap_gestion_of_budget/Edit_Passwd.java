package com.example.myaap_gestion_of_budget;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_Passwd extends Activity {

    public EditText Curr_passwd, New_passwd, Conf_new_passwd;
    private Button Edit_passwd_btn;
    private FirebaseAuth mAuth;
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_passwd);

        Curr_passwd = findViewById(R.id.Curr_passwd);
        New_passwd = findViewById(R.id.New_Passwd);
        Conf_new_passwd = findViewById(R.id.Conf_new_passwd);
        Edit_passwd_btn = findViewById(R.id.Edit_passwd_btn);
        mAuth = FirebaseAuth.getInstance();
        SessionManagement sessionManagement=new SessionManagement(Edit_Passwd.this) ;
        String username= sessionManagement.getSession();


        Edit_passwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CurrPasswd = Curr_passwd.getText().toString();
                String NewPasswd = New_passwd.getText().toString();
                String ConfNewPasswd = Conf_new_passwd.getText().toString();

                if (TextUtils.isEmpty(CurrPasswd) || TextUtils.isEmpty(NewPasswd) || TextUtils.isEmpty(ConfNewPasswd)) {
                    Toast.makeText(getApplicationContext(), "Your password won't be changed ,please fill all fields !", Toast.LENGTH_SHORT).show();
                    //Current passwd is correct olla oho !!?
                }


                databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(username)) {
                            final String password_Strg = snapshot.child("users").child("passwordString").getValue(String.class);
                            if (password_Strg.equals(CurrPasswd)) {
                                if ((NewPasswd).equals(ConfNewPasswd)) {

                                    databasereference.child("users").child(username).child("passwordString").setValue(NewPasswd);

                                } else {
                                    Toast.makeText(Edit_Passwd.this, " Passwords don't match", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Edit_Passwd.this, "Your current password is not correct !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

    }

}

