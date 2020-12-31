package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    public ListItemAdapter adapter;
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ListItemAdapter();

        listView = (ListView) findViewById(R.id.main_listview);
        btn = (Button) findViewById(R.id.button);

        listView.setAdapter(adapter);

        add(getJsonString());

        adapter.notifyDataSetChanged();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
                Log.d("test","confirm");
            }
        });


    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getAssets().open("phone.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "Utf-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    private void add(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray movieArray = jsonObject.getJSONArray("Contents");

            for(int i=0; i<movieArray.length(); i++)
            {
                JSONObject movieObject = movieArray.getJSONObject(i);

                //ListItem item = new ListItem();

                adapter.addItem(movieObject.getString("Name"),
                        movieObject.getString("Number"),movieObject.getString("Email"));

            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
}