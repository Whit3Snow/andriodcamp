package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class SubActivity extends AppCompatActivity {

    public Button ADD;
    public EditText Name;
    public EditText Number;
    public EditText Email;

    private Context context;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);

        ADD = (Button)findViewById(R.id.button);
        Name = (EditText)findViewById(R.id.editText1);
        Number = (EditText)findViewById(R.id.editText2);
        Email = (EditText)findViewById(R.id.editText3);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String number = Number.getText().toString();
                String email = Email.getText().toString();
                Log.d("test",name);
                //insert(name,number,email);

            }


        });
    }

    private void insert(String name,String number,String email){

        FileOutputStream outputStream;
        ListItem listitem = new ListItem();
        listitem.setName(name);
        listitem.setPhone(number);
        listitem.setEmail(email);

        try {
            //FileWriter file = new FileWriter("c:\\myJson.json");

            outputStream = openFileOutput("phone.json", Context.MODE_PRIVATE);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name",listitem.getName());
            jsonObj.put("number",listitem.getPhone());
            jsonObj.put("email",listitem.getEmail());
            outputStream.write(jsonObj.toString().getBytes());
            //file.write(String.valueOf(jsonObj.toString().getBytes()));
            outputStream.close();
            Log.d("JSON" , jsonObj.toString());
            //file.close();
        }
        catch (IOException | JSONException ex)
        {
            ex.printStackTrace();
        }



    }


}
