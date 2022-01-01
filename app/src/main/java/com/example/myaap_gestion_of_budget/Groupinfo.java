package com.example.myaap_gestion_of_budget;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Groupinfo extends AppCompatActivity {
    private Button button1;
    private ImageButton button3;
    EditText searches;
    TextView groupname ;
    TextView admin ;
    TextView Idd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_info);
        button1 = (Button) findViewById(R.id.button1);
        groupname = findViewById(R.id.textView4);
        admin = findViewById(R.id.textView);
        Idd = findViewById(R.id.textView2);
        button3 = findViewById(R.id.imageButton4);
        searches = findViewById(R.id.searchgrou);
            showgroupdata();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivit.class);
                startActivity(intent);
            }
        });


    }

    private void showgroupdata() {
            Intent intent = getIntent();
            String group_name = intent.getStringExtra("Group Name");
            String id_ = intent.getStringExtra("Id");
            String group_admin = intent.getStringExtra("Group Admin");
        groupname.setText(group_name);
        admin.setText(group_admin);
        Idd.setText(id_);

    }
}
