package com.example.myaap_gestion_of_budget;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.ValueEventListener;

public class GroupActions extends AppCompatActivity {
    private Button addBudgetB;
    private  Intent intent = getIntent();
    DatabaseReference databasereference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://myaapgestionofbudget-default-rtdb.firebaseio.com/");
    private  String groupid ;
    private String Groupname;
    private String groupAdmin ;
    private ImageButton copyid;
    SessionManagement sessionManagement;
    //------------------menu top------------------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int item_id=item.getItemId();
        sessionManagement=new SessionManagement(GroupActions.this) ;
        if(item_id==R.id.logout){
            sessionManagement.removeSession();
            Intent i = new Intent(GroupActions.this,Start.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();

        }

        if (item_id==R.id.change_password){
            Intent intent=new Intent(GroupActions.this,Edit_Passwd.class);
            startActivity(intent);
        }
        return true;
    }
    //--------------------------end here----------------------------------------------------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_actions);
        this.addBudgetB = findViewById(R.id.toaddbudget);
        Button showdashboard = findViewById(R.id.showdashboard);

        //get Seesion
        SessionManagement sessionManagement = new SessionManagement(GroupActions.this);
        String username = sessionManagement.getSession();
        ////////////////////////////////////////

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
                groupAdmin = snapshot.child("Group Admin").getValue().toString();
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


        copyid = (ImageButton) findViewById(R.id.copyId);

        copyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(
                        "text label", // What should I set for this "label"?
                        groupid);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(GroupActions.this, "Saved to clip board", Toast.LENGTH_SHORT).show();
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


        //disable Button if the user session is not admin of the group
        Button deletebtn = (Button) findViewById(R.id.deleteg);


        DatabaseReference groups = FirebaseDatabase.getInstance().getReference("Groups");
        DatabaseReference group_enrolments = FirebaseDatabase.getInstance().getReference("group_enrolments");
        DatabaseReference User_enrolments = FirebaseDatabase.getInstance().getReference("User_enrolments");


        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(GroupActions.this, "Delete Clicked ", Toast.LENGTH_SHORT).show();

                //Log.i("22"  , groupAdmin);
                //  Log.i("4" , username);
                //    Log.i("44" , String.valueOf(username.equals(groupAdmin)));

                groups.child(groupid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String Admin_value_db = snapshot.child("Group Admin").getValue().toString();

                        if (Admin_value_db.equals(username)) {

                            User_enrolments.child( username).child(groupid).removeValue();
                            group_enrolments.child(groupid).removeValue();



                            Toast.makeText(GroupActions.this, "You have successfully left the group ! ", Toast.LENGTH_SHORT).show();
                            Intent intent3 = new Intent(getApplicationContext(), Listegroup.class);
                            startActivity(intent3);



                        } else {
                            User_enrolments.child( username).child(groupid).removeValue();
                            group_enrolments.child( groupid).child(username).removeValue();

                            Toast.makeText(GroupActions.this, "You have successfully left the group ! ", Toast.LENGTH_SHORT).show();
                            Intent intent3 = new Intent(getApplicationContext(), Listegroup.class);
                            startActivity(intent3);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        Toast.makeText(GroupActions.this, "hanta rja3tu ", Toast.LENGTH_SHORT).show();
        finish();
    }
}
