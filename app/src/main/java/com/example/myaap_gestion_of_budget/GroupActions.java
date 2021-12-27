package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GroupActions extends AppCompatActivity {
    private Button addBudgetB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_actions);
        this.addBudgetB = findViewById(R.id.toaddbudget);
        Button showdashboard = findViewById(R.id.showdashboard);


        addBudgetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity =  new Intent(getApplicationContext(),  addBudget.class);
                startActivity(otherActivity);

            }});

        showdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GroupActions.this , Dashboard.class));

            }
        });

    }
}