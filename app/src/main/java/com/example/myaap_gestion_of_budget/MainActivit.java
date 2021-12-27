package com.example.myaap_gestion_of_budget;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivit extends AppCompatActivity {
    private Button button;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivit.this, "This ID  is not existe ", Toast.LENGTH_SHORT).show();
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        setContentView(R.layout.group_info);
        button1 = (Button) findViewById(R.id.button1);


    }
    public void openActivity3 () {
        setContentView(R.layout.activity_main);
    }
}