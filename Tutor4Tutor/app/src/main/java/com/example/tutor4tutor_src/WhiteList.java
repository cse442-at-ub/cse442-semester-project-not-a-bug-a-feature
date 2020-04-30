package com.example.tutor4tutor_src;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WhiteList extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_whitelistpage);
            AsyncTask<String, Integer, String> str1 = new DatabaseHelper().execute("get","whitelist.txt",UserStates.INSTANCE.getUsername());
            final ArrayList<String> reslist = new ArrayList<String>();
            try {
                JSONObject jo  = new JSONObject(str1.get());
                reslist.addAll(Arrays.asList(jo.getString("whitelist").split(",")));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListView mListView = findViewById(R.id.whitelist_userlist);
            //JSONObject jo  = new JSONObject(data);
            ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, reslist);
            mListView.setAdapter(arrayAdapter);

        }
    }
