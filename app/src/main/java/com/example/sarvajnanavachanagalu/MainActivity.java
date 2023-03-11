package com.example.sarvajnanavachanagalu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import com.example.sarvajnanavachanagalu.adapter.MyAdapter;
import com.example.sarvajnanavachanagalu.databinding.ActivityMainBinding;
import com.example.sarvajnanavachanagalu.models.Vachanagalu;
import com.example.sarvajnanavachanagalu.views.AboutUsActivity;
import com.example.sarvajnanavachanagalu.views.AuthorActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        List<Vachanagalu> vachanagaluList = new ArrayList<>();

// Showing ads on the main screen
        MobileAds.initialize(this, initializationStatus -> {
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);


// onClick for opening menu
        binding.topAppBar.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case R.id.about_us:
                    Intent i = new Intent(this, AboutUsActivity.class);
                    startActivity(i);
                    break;


                case R.id.about_author:
                    Intent i2 = new Intent(this, AuthorActivity.class);
                    startActivity(i2);
                    break;

                case R.id.share:
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                        String shareMessage = "\nLet me recommend you this application\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch (Exception e) {
                        //e.toString();
                    }
                    break;

                default:

                    break;


            }
            return super.onOptionsItemSelected(item);

        });


// Reading the data from the json file
        try {
            JSONObject obj = new JSONObject(loadJSONfromAssests());

            JSONArray vachanArray = obj.getJSONArray("vachanagalu");
            for (int i = 0; i < vachanArray.length(); i++) {
                JSONObject vachana = vachanArray.getJSONObject(i);
                vachanagaluList.add(new Vachanagalu(vachana.getString("vachana")));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

// Setting the main recyclerview
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MyAdapter(getApplicationContext(), vachanagaluList));


    }

    private String loadJSONfromAssests() {
        String json = null;
        try {
            InputStream is = getAssets().open("vachanagalu.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return json;
    }


}