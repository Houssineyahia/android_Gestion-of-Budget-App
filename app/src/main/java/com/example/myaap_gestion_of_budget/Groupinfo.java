package com.example.myaap_gestion_of_budget;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.AppCompatActivity;

public class Groupinfo extends AppCompatActivity {
    private Button button1;
    TextView groupname ;
    TextView admin ;
    TextView id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_info);
        button1 = (Button) findViewById(R.id.button1);
        groupname = findViewById(R.id.textView4);
        admin = findViewById(R.id.textView);
        id = findViewById(R.id.textView2);
        showgroupdata();
    }

    private void showgroupdata() {
            Intent intent = getIntent();
            String group_name = intent.getStringExtra("Group Name");
            String id_ = intent.getStringExtra("Id");
            String group_admin = intent.getStringExtra("Group Admin");
        groupname.setText(group_name);
        admin.setText(group_admin);
        id.setText(id_);

    }
}
