package com.example.json_parsing_lesson3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> personName = new ArrayList<String>();
    ArrayList<String> emailLinks= new ArrayList<String>();
    ArrayList<String> mobileNumbers= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        try{
            JSONObject obj = new JSONObject(loadJSONfromAssets());

            JSONArray jsonArray = obj.getJSONArray("users");

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject userData = jsonArray.getJSONObject(i);
                personName.add(userData.getString("name"));
                emailLinks.add(userData.getString("email"));

                JSONObject contactJSON = userData.getJSONObject("contact");
                mobileNumbers.add(contactJSON.getString("mobile"));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personName, emailLinks, mobileNumbers);
        recyclerView.setAdapter(customAdapter);

    }

    private String loadJSONfromAssets() {
        String json = null;
        try{
            InputStream is = getAssets().open("users_list.json");

            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            return json;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}