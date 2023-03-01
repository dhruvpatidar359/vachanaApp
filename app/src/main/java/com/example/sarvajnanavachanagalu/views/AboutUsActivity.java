package com.example.sarvajnanavachanagalu.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sarvajnanavachanagalu.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us2);

        AdView adView = findViewById(R.id.adView);
        MobileAds.initialize(this, initializationStatus -> {
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
}