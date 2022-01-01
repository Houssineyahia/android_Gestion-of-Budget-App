package com.example.myaap_gestion_of_budget;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {
    private static int SPLASH=3000;
    Animation animation;
    private ImageView imageView;
    private TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

            animation = AnimationUtils.loadAnimation(this,R.anim.animation);
            imageView=findViewById(R.id.imageView);
         //   appName=findViewById(R.id.appName);
            imageView.setAnimation(animation);
           // appName.setAnimation(animation);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Start.this,Sign_Up.class);
                    startActivity(intent);
                    finish();
                }
            },SPLASH);

        }
    }


