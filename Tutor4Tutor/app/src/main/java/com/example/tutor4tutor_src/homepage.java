package com.example.tutor4tutor_src;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class homepage extends AppCompatActivity {



    EditText textView5, text;
    Button button;
    Button server_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, login.class);
                startActivity(intent);

            }
        });
        server_btn = (Button)findViewById(R.id.serverlogin_btn);
        server_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, wenxuan_login.class);
                startActivity(intent);
            }

        });
        final String tests ="";
        try {

            Map<String, String> hm = new HashMap<String, String>();
            hm.put("data","lkj");
            hm.put("mnn","test");
            JSONObject jo = new JSONObject(hm);
            AsyncTask<String, Integer, String> str1 = new DatabaseHelper().execute("set","mdb.txt","yang",jo.toString());

            //new DatabaseHelper().execute("set","mdb.txt","yang",jo.toString());
            Toast.makeText(getApplicationContext(), str1.get(), Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AsyncTask<String, Integer, String> jsonstr = new DatabaseHelper().execute("get","mdb.txt","yang");
        try {
            String data = jsonstr.get();
            JSONObject jo  = new JSONObject(data);
            Toast.makeText(getApplicationContext(), jo.getString("data"), Toast.LENGTH_LONG).show();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}







