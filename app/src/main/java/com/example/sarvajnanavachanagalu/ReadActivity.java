package com.example.sarvajnanavachanagalu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {
TextView read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        read = findViewById(R.id.read_text);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        read.setText(Html.fromHtml(data));
    }
}