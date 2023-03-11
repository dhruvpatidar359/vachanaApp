package com.example.sarvajnanavachanagalu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ReadActivity extends AppCompatActivity {
    TextView read;
    AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        read = findViewById(R.id.read_text);
        adview = findViewById(R.id.adView);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        read.setText(Html.fromHtml(data));


        MobileAds.initialize(this, initializationStatus -> {
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
    }
}