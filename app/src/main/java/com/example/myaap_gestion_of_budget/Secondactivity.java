package com.example.myaap_gestion_of_budget;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Secondactivity<Imagebutton> extends AppCompatActivity {
    private ImageButton commence;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);
         commence = findViewById(R.id.commencons);
       commence.setOnClickListener(v -> {
           Intent intent=new Intent(getApplicationContext(), Sign_Up.class);
           startActivity(intent);
       });
    }
}
