package com.example.myaap_gestion_of_budget;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Edit_Passwd extends AppCompatActivity {

    public EditText Curr_passwd, New_passwd, Conf_new_passwd;
    private Button Edit_passwd_btn;
    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private TextView textView;

    DatabaseReference DBRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");

    SessionManagement sessionManagement;
    //------------------menu top------------------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int item_id=item.getItemId();
        sessionManagement=new SessionManagement(Edit_Passwd.this) ;
        if(item_id==R.id.logout){
            sessionManagement.removeSession();
            Intent i = new Intent(Edit_Passwd.this,Start.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();

        }

        return true;
    }
    //--------------------------end here----------------------------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_passwd);

        Curr_passwd = findViewById(R.id.Curr_passwd);
        New_passwd = findViewById(R.id.New_Passwd);

        Conf_new_passwd = findViewById(R.id.Conf_new_passwd);
        Edit_passwd_btn = findViewById(R.id.Edit_passwd_btn);
       
        mAuth = FirebaseAuth.getInstance();
       sessionManagement=new SessionManagement(Edit_Passwd.this) ;
        String username = sessionManagement.getSession();


        DBRef = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("passwordString");


        Edit_passwd_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String CurrPasswd = Curr_passwd.getText().toString();
               String NewPasswd = New_passwd.getText().toString();
               String ConfNewPasswd = Conf_new_passwd.getText().toString();

               DBRef.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        //Testing_Example OOook
                        String password_db = snapshot.getValue().toString();
                       



                        if (TextUtils.isEmpty(CurrPasswd) || TextUtils.isEmpty(NewPasswd) || TextUtils.isEmpty(ConfNewPasswd)) {
                            Toast.makeText(getApplicationContext(), "Your password won't be changed ,please fill all fields !", Toast.LENGTH_SHORT).show();

                            //Current passwd is correct olla oho !!?
                        } else if ( !(password_db.equals(CurrPasswd)) ) {
                            Toast.makeText(Edit_Passwd.this, "Your current password is not correct !", Toast.LENGTH_SHORT).show();  }

                           else if (!(NewPasswd.equals(ConfNewPasswd))) {

                                Toast.makeText(Edit_Passwd.this, " Passwords don't match", Toast.LENGTH_SHORT).show();
                            }

                            else {
                             DatabaseReference DBRef2 = FirebaseDatabase.getInstance().getReference().child("users").child(username);
                            HashMap MyHashMap = new HashMap();
                            MyHashMap.put("passwordString",NewPasswd);

                            DBRef2.updateChildren(MyHashMap);
                            Toast.makeText(Edit_Passwd.this, " password has been successfully changed !", Toast.LENGTH_SHORT).show();



                            }


                    }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });

           }
       });

 /*
        Edit_passwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CurrPasswd = Curr_passwd.getText().toString();
                String NewPasswd = New_passwd.getText().toString();
                String ConfNewPasswd = Conf_new_passwd.getText().toString();


                databasereference.


                databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(username1)) {
                                final String password_Strg = snapshot.child("users").child("passwordString").getValue(String.class);

                                if (TextUtils.isEmpty(CurrPasswd) || TextUtils.isEmpty(NewPasswd) || TextUtils.isEmpty(ConfNewPasswd)) {
                                    Toast.makeText(getApplicationContext(), "Your password won't be changed ,please fill all fields !", Toast.LENGTH_SHORT).show();

                                    //Current passwd is correct olla oho !!?
                                } else if ( !(password_Strg.equals(CurrPasswd)) ) {
                                    Toast.makeText(Edit_Passwd.this, "Your current password is not correct !", Toast.LENGTH_SHORT).show();

                                 if (!(NewPasswd.equals(ConfNewPasswd))) {

                                        Toast.makeText(Edit_Passwd.this, " Passwords don't match", Toast.LENGTH_SHORT).show(); }

                                       else {

                                     mFirebaseUser.updatePassword(NewPasswd);

                                                        Toast.makeText(Edit_Passwd.this, " Safi it's done", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Edit_Passwd.this, Profil.class));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    }); */
                }
   

        }


