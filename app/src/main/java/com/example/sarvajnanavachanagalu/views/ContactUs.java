package com.example.sarvajnanavachanagalu.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sarvajnanavachanagalu.R;

public class ContactUs extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        textView = findViewById(R.id.dummy4);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");

// Put extra information into the Intent, including the email address
// that you wish to send to, and any subject (optional, of course).
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"avruttiworldwide@gmail.com"});
                startActivity(Intent.createChooser(emailIntent, "Contact US."));
            }
        });


    }

}