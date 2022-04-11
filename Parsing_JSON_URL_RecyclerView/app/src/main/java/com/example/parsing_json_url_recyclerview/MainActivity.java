package com.example.parsing_json_url_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL = "https://run.mocky.io/v3/d642e2f6-0016-4698-b609-8f676b62ecea";

    List<MovieModelClass> data;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);

        data = new ArrayList<>();


        GetData getData = new GetData();
        getData.execute();

    }

    public class GetData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection connection = null;

                try{
                    url = new URL(JSON_URL);
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream stream = connection.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(stream);

                    int data = streamReader.read();
                    while(data != -1){
                        current += (char)data;
                        data = streamReader.read();
                    }

                    return current;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(current != null){
                        connection.disconnect();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{
                JSONObject objectJSON = new JSONObject(s);
                JSONArray arrayJSON = objectJSON.getJSONArray("movies");

                for (int i = 0; i < arrayJSON.length(); i++){
                    JSONObject jsonObjectTMP = arrayJSON.getJSONObject(i);

                    MovieModelClass mmcTMP = new MovieModelClass(jsonObjectTMP.getString("id"), jsonObjectTMP.getString("name"), jsonObjectTMP.getString("image"));

                    data.add(mmcTMP);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecycleView(data);
        }



    }

    private void PutDataIntoRecycleView(List<MovieModelClass> movieList){
        Adapter_ adapter = new Adapter_(this, movieList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

    }
}