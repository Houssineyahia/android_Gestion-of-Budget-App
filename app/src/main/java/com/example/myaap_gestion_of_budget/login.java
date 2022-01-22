package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.example.myaap_gestion_of_budget.models.User;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    //private EditText email1, pswd;
    // private Button Connect_Btn;
    // private FirebaseAuth mAuth;
    //private TextView join;

    public EditText inputEmail, inputPassword;
    private Button btnSignup;
    private FirebaseAuth mAuth;
    private TextView btnLogin;
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    String Sitekey = "6Ldjg90dAAAAANay5R-R-QlnhK2Km_MRBGQEd85E";
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.username);
        inputPassword = findViewById(R.id.passwd);
        btnSignup = findViewById(R.id.Connect_Btn);
        btnLogin = findViewById(R.id.joinus);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, Sign_Up.class));
            }
        });



        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userString = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(userString) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();

                } else {
                    databasereference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(userString)) {
                                final String getpassword = snapshot.child(userString).child("passwordString").getValue(String.class);
                                if (getpassword.equals(password)) {
                                    Toast.makeText(login.this, "good", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this, Listegroup.class));
                                    //1. login and save session
                                    User user = new User(userString);
                                    SessionManagement sessionManagement = new SessionManagement(login.this);
                                    sessionManagement.saveSession(user);

                                } else {
                                    Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(login.this, "wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(login.this);
        String username = sessionManagement.getSession();

        if(username.equals("none")){
            //user id logged in and so move to mainActivity

        }else{
            startActivity(new Intent(login.this, Listegroup.class));

        }
    }


}


