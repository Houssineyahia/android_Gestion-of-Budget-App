package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GroupActions extends AppCompatActivity {
    private Button addBudgetB;
    private  Intent intent = getIntent();
    DatabaseReference databasereference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    private  String groupid ;
    private String Groupname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_actions);
        this.addBudgetB = findViewById(R.id.toaddbudget);
        Button showdashboard = findViewById(R.id.showdashboard);

        TextView AgroupId = (TextView) findViewById(R.id.AgroupId);
        TextView Gdesc = (TextView) findViewById(R.id.grouD);
        TextView gtype = (TextView) findViewById(R.id.gtype);
        TextView title = (TextView) findViewById(R.id.title);

        AgroupId.setText(getIntent().getStringExtra("GroupId"));
        groupid = getIntent().getStringExtra("GroupId");


        databasereference.child("Groups").child(groupid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Gdesc.setText(snapshot.child("Description").getValue().toString());
                gtype.setText(snapshot.child("Group Type").getValue().toString());
                title.setText(snapshot.child("Group Name").getValue().toString());
                Groupname = snapshot.child("Group Name").getValue().toString();
                if(snapshot.child("Group Type").getValue().toString().equals("i")){
                    gtype.setText("Indevidual");
                }else{
                    gtype.setText("Group");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        

        addBudgetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity =  new Intent(getApplicationContext(),  addBudget.class);
                otherActivity.putExtra("groupid" , groupid);
                otherActivity.putExtra("groupname" , Groupname);
                startActivity(otherActivity);

            }});

        showdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherActivity =  new Intent(getApplicationContext(),  Dashboard.class);
                otherActivity.putExtra("groupid" , groupid);
                startActivity(otherActivity);


            }
        });

    }
}