package com.triana.libreria_;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class animationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(animationActivity.this,MainActivity.class));
                finish();
            }
        }, 5000);

    }
}