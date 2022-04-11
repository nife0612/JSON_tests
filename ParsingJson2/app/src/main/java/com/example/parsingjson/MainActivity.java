package com.example.parsingjson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listy;

    String json_string = "{\"title\":\"JSONParserTutorial\",\"array\":[{\"company\":\"Google\"},{\"company\":\"Facebook\"},{\"company\":\"LinkedIn\"},{\"company\":\"Microsoft\"},{\"company\":\"Apple\"}],\"nested\":{\"flag\":true,\"random_number\":1}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            listy = findViewById(R.id.list_view);

            List<String> items = new ArrayList<>();

            JSONObject root = new JSONObject(json_string);

            JSONArray array = root.getJSONArray("array");

            this.setTitle(root.getString("title"));

            for (int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                items.add(object.getString("company"));
            }

            // Creating Adapter for the ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , items);

            if(listy != null){
                listy.setAdapter(adapter);
            }
            JSONObject nested = root.getJSONObject("nested");
            Log.d("TAG", "flag value is " + nested.getString("flag"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}