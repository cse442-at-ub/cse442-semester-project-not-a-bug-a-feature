package com.example.tutor4tutor_src;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ListUserPage extends AppCompatActivity {
    Boolean isInSelectMode = false;
    String selectedName = "";
    ArrayList<String> resourceslist = new ArrayList<String>();
    ArrayList<Boolean> isSelected = new ArrayList<Boolean>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        Button add = (Button) findViewById(R.id.userliset_addbtn);
        final Button selectMode = (Button) findViewById(R.id.userliset_selectbtn);
        final Button whitelist= (Button)findViewById(R.id.userlist_whitelist);
        AsyncTask<String, Integer, List<? extends String>> str1 = new ServerHelper().execute("get","userstatus.txt");

        try {
            for (String item : str1.get()) {
                resourceslist.add(item);
                isSelected.add(Boolean.FALSE);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final ListView mListView = findViewById(R.id.userlist_userlist);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setItemChecked(2,true);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, resourceslist);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),resourceslist.get(position),Toast.LENGTH_LONG).show();
                UserStates.INSTANCE.setSelectUsername(resourceslist.get(position));
                SparseBooleanArray sp=mListView.getCheckedItemPositions();
                List<String> str = new ArrayList<String>();
                selectedName +=(resourceslist.get(position) + ",");
                Toast.makeText(getApplicationContext() ,selectedName, Toast.LENGTH_SHORT).show();

                if(UserStates.INSTANCE.isInSelectMode()) {
                    isSelected.set(position,!isSelected.get(position));
                    if (isSelected.get(position))
                        mListView.getChildAt(position).setBackgroundColor(Color.RED);
                    else
                        mListView.getChildAt(position).setBackgroundColor(Color.WHITE);
                }
                else if(!UserStates.INSTANCE.isInSelectMode())
                {
                    String name = resourceslist.get(position);
                    AsyncTask<String, Integer, String> jsonstr = new DatabaseHelper().execute("get","whitelist.txt",name);
                    JSONObject jo  = null;
                    Boolean c = false;
                    try {
                        jo = new JSONObject(jsonstr.get());
                        if (Arrays.asList(jo.getString("whitelist").split(",")).contains(UserStates.INSTANCE.getUsername()))
                        {
                            c = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (c) {
                        Intent intent = new Intent(ListUserPage.this, UserCommentPage.class);
                        startActivity(intent);
                    }
                }
            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receivedDate = "";

                AsyncTask<String, Integer, String> jsonstr = new DatabaseHelper().execute("get","mdb.txt","yang");
                try {
                    if (jsonstr.get() != "")
                    {
                        String data = jsonstr.get();
                        JSONObject jo = new JSONObject(data);
                        receivedDate = jo.getString("whitelist");
                        Toast.makeText(getApplicationContext(), jo.getString("whitelist"), Toast.LENGTH_LONG).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    Map<String, String> hm = new HashMap<String, String>();
                    //selectedName = selectedName.substring(0,selectMode.length()-1);
                    String[] l = receivedDate.split(",");
                    String[] targets = selectedName.split(",");
                    for (int i = 0 ; i < targets.length ; i++) {
                        for(int p = 0 ; p < l.length ;p++)
                        {
                            if (targets[i] == l[p])
                            {
                                continue;
                            }
                        }
                        receivedDate+=(targets[i]+",");
                    }

                    hm.put("whitelist",receivedDate);
                    JSONObject jo = new JSONObject(hm);
                    AsyncTask<String, Integer, String> str1 = new DatabaseHelper().execute("set","whitelist.txt",UserStates.INSTANCE.getUsername(),jo.toString());
                    //new DatabaseHelper().execute("set","mdb.txt","yang",jo.toString());
                    Toast.makeText(getApplicationContext(), "add " +selectedName +" to white list", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();
                startActivity(getIntent());

            }
        });

        selectMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserStates.INSTANCE.setInSelectMode(!UserStates.INSTANCE.isInSelectMode());
                finish();
                startActivity(getIntent());
            }
        });


        whitelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListUserPage.this, WhiteList.class);
                startActivity(intent);
            }
        });


    }
}