package com.example.myaap_gestion_of_budget;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaap_gestion_of_budget.models.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Groupinfo extends AppCompatActivity {
    private Button button1;
    private ImageButton button3;
    EditText searches;
    TextView groupname ;
    TextView admin ;
    TextView Idd ;
    SessionManagement sessionManagement;
    DatabaseReference group_enrolments= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    DatabaseReference User_enrolments= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    //------------------menu top------------------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int item_id=item.getItemId();
        sessionManagement=new SessionManagement(Groupinfo.this) ;
        if(item_id==R.id.logout){
            sessionManagement.removeSession();
            Intent i = new Intent(Groupinfo.this,Start.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();

        }

        if (item_id==R.id.change_password){
            Intent intent=new Intent(Groupinfo.this,Edit_Passwd.class);
            startActivity(intent);
        }
        return true;
    }
    //--------------------------end here----------------------------------------------------//
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
        group_enrolments = FirebaseDatabase.getInstance().getReference("group_enrolments");
        User_enrolments = FirebaseDatabase.getInstance().getReference("User_enrolments");
        Intent intent = getIntent();
        String group_name = intent.getStringExtra("Group Name");
        String id_ = intent.getStringExtra("Id");
        String group_admin = intent.getStringExtra("Group Admin");
        groupname.setText(group_name);
        admin.setText(group_admin);
        Idd.setText(id_);
        SessionManagement sessionManagement=new SessionManagement(Groupinfo.this) ;
        String username= sessionManagement.getSession();


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivit.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                group_enrolments.child(id_).child( username).setValue("true");
                User_enrolments.child( username).child(id_).setValue("true");
                Toast.makeText(Groupinfo.this, "Group has been successfully joined ! ", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
