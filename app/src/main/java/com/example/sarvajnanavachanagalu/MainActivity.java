package com.example.sarvajnanavachanagalu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sarvajnanavachanagalu.adapter.MyAdapter;
import com.example.sarvajnanavachanagalu.databinding.ActivityMainBinding;
import com.example.sarvajnanavachanagalu.models.Vachanagalu;
import com.example.sarvajnanavachanagalu.views.AboutUsActivity;
import com.example.sarvajnanavachanagalu.views.AuthorActivity;
import com.example.sarvajnanavachanagalu.views.ContactUs;
import com.example.sarvajnanavachanagalu.views.PrivacyPolicy;
import com.example.sarvajnanavachanagalu.views.TermsConditions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.transition.MaterialContainerTransform;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        List<Vachanagalu> vachanagaluList = new ArrayList<>();







        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3201490891635473/3868315366", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("Interstitial", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("Interstitial", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });


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
                case R.id.privacy_policy:
                    Intent i3 = new Intent(this, PrivacyPolicy.class);
                    startActivity(i3);
                    break;

                case R.id.terms_conditions:
                    Intent i4 = new Intent(this, TermsConditions.class);
                    startActivity(i4);
                    break;

                case R.id.contact_us:
                    Intent i5 = new Intent(this, ContactUs.class);
                    startActivity(i5);
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

            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return json;
    }
// Loading the ad again
    @Override
    protected void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3201490891635473/3868315366", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("Interstitial", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("Interstitial", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        Log.d("Dhruv", String.valueOf(randomNumber));
        if (randomNumber == 2) {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(MainActivity.this);
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
        }


    }
}