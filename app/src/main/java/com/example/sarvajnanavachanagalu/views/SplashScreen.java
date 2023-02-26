package com.example.sarvajnanavachanagalu.views;

import static javax.xml.datatype.DatatypeConstants.DURATION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sarvajnanavachanagalu.MainActivity;
import com.example.sarvajnanavachanagalu.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(() -> {

            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);

            finish();
        }, 2000);
    }

    }
