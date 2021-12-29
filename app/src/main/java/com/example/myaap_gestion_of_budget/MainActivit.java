package com.example.myaap_gestion_of_budget;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText searches;
    DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    TextView groupname ;
    TextView admin ;
    TextView Idd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       groupname = findViewById(R.id.textView4);
        admin = findViewById(R.id.textView);
        Idd = findViewById(R.id.textView2);

        button = (Button) findViewById(R.id.button);
        searches = findViewById(R.id.searchgrou);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Groups");

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            String sear = searches.getText().toString();
            Query checkGroup = mDatabase.orderByChild("Id").equalTo(sear);

                if(sear.isEmpty()) {
                Toast.makeText(MainActivit.this, "Please fill the fields ", Toast.LENGTH_SHORT).show();
                }
                else

            {
                checkGroup.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            String GroupName = dataSnapshot.child(sear).child("Group Name").getValue(String.class);
                            String id = dataSnapshot.child(sear).child("Id").getValue(String.class);
                            String GroupAdmin = dataSnapshot.child(sear).child("Group Admin").getValue(String.class);
                          /* openActivity2( GroupName,  id ,GroupAdmin );
                            groupname.setText(GroupName);
                            admin.setText(GroupAdmin);
                            Idd.setText(id);*/
                          Intent intent = new Intent(getApplicationContext(), Groupinfo.class);
                            intent.putExtra("Group Name", GroupName);
                            intent.putExtra("Id", id);
                            intent.putExtra("Group Admin", GroupAdmin);
                           startActivity(intent);

                        } else {
                            Toast.makeText(MainActivit.this, "This ID  is not existe ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                   /* if(sear.equals(s)!=true){
                Toast.makeText(MainActivit.this, "This ID  is not existe ", Toast.LENGTH_SHORT).show(); }
                  else {
                        openActivity2();
                   }*/
                });
            }

        } }) ;
    }

    public void openActivity2(String GroupName, String id , String GroupAdmin ) {
        setContentView(R.layout.group_info);
        button1 = (Button) findViewById(R.id.button1);
        groupname = findViewById(R.id.textView4);
        admin = findViewById(R.id.textView);
        Idd = findViewById(R.id.textView2);
        groupname.setText(GroupName);
        admin.setText(GroupAdmin);
        Idd.setText(id);

    }

}