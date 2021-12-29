package com.example.myaap_gestion_of_budget;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "This ID  is not existe ", Toast.LENGTH_SHORT).show();
/*
        //initiaize and assign variables

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set dashboard select

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        //perform ItemSelectListener

        bottomNavigationView.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.dashboard:

                        return true;

                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(),Profil.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(),Add__.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.groups:
                        startActivity(new Intent(getApplicationContext(),Groups.class));
                        overridePendingTransition(0,0);

                        return true;
                }
                return false;
            }


        });*/
    }

    }


