package com.example.json_lesson_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    String name, age;

    private static final String JSON_URL = "https://run.mocky.io/v3/373e4d79-7575-4791-9e7f-89a0441ed8b1";

    ArrayList<HashMap<String, String>> friendsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friendsList = new ArrayList<>();

        lv = findViewById(R.id.listview);

        GetData getData = new GetData();

        getData.execute();

    }




    public class GetData extends AsyncTask<String, String, String>{


        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try{
                URL url;

                HttpURLConnection urlConnection = null;


                try{
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStr = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStr);

                    int data = inputStreamReader.read();
                    while (data != -1){
                        current += (char) data;
                        data = inputStreamReader.read();
                    }

                    return current;


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        protected void onPostExecute(String s){

            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("Friend");
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject tmp = jsonArray.getJSONObject(i);
                    name = tmp.getString("name");
                    age = tmp.getString("age");

                    // HashMap:
                    HashMap<String, String> friends = new HashMap<>();

                    friends.put("name", name);
                    friends.put("age", age);

                    friendsList.add(friends);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    friendsList,
                    R.layout.row_layout,
                    new String[]{"name", "age"},
                    new int[]{R.id.textView, R.id.textView2}
            );

            lv.setAdapter(adapter);
        }



    }




}
