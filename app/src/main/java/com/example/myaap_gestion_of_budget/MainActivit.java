package com.example.myaap_gestion_of_budget;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivit extends AppCompatActivity {
    private Button button;
    private Button button1;
    SearchView searches;
    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    String groupname ;
    String admin ;
    String Idd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
        button = (Button) findViewById(R.id.button);
        searches = findViewById(R.id.searchgrou);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Groups");

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            String sear = searches.getQuery().toString();

                if(sear.isEmpty()) {
                Toast.makeText(MainActivit.this, "Please fill the fields ", Toast.LENGTH_SHORT).show();
                }
                else

            {
                mDatabase.child(sear).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String GroupName = dataSnapshot.child("Group Name").getValue(String.class);
                            String GroupAdmin = dataSnapshot.child("Group Admin").getValue(String.class);
                          Intent intent = new Intent(getApplicationContext(), Groupinfo.class);
                            intent.putExtra("Group Name", GroupName);
                            intent.putExtra("Id", sear);
                            intent.putExtra("Group Admin", GroupAdmin);
                           startActivity(intent);

                        } else {
                            Toast.makeText(MainActivit.this, "This ID  is not existe ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
            }

        } }) ;
    }



}