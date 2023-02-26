package com.example.sarvajnanavachanagalu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.sarvajnanavachanagalu.adapter.MyAdapter;
import com.example.sarvajnanavachanagalu.databinding.ActivityMainBinding;
import com.example.sarvajnanavachanagalu.models.Vachanagalu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        List<Vachanagalu> vachanagaluList = new ArrayList<>();




        binding.topAppBar.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case R.id.settings:
                    Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(i);


                default:
                    Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();
                    break;


            }
            return super.onOptionsItemSelected(item);

        });



try{
    JSONObject obj = new JSONObject(loadJSONfromAssests());

    JSONArray vachanArray = obj.getJSONArray("vachanagalu");
    for(int i = 0 ; i < vachanArray.length() ;i++){
        JSONObject vachana = vachanArray.getJSONObject(i);
        vachanagaluList.add(new Vachanagalu(vachana.getString("vachana")));
    }

} catch (Exception e) {
    throw new RuntimeException(e);
}


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MyAdapter(getApplicationContext(),vachanagaluList));


    }

    private String loadJSONfromAssests() {
        String json = null;
        try{
            InputStream is = getAssets().open("vachanagalu.json");
            int size = is.available();

            byte[] buffer  = new byte[size];
            is.read(buffer);
            is.close();

            json  = new String(buffer,"UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
return json;
    }


}