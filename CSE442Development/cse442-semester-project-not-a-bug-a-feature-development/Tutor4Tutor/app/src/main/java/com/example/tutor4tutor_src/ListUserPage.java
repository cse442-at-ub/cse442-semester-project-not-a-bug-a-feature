package com.example.tutor4tutor_src;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListUserPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        AsyncTask<String, Integer, List<? extends String>> str1 = new ServerHelper().execute("get","userstatus.txt");
        final ArrayList<String> resourceslist = new ArrayList<String>();
        try {
            for (String item : str1.get()) {
                resourceslist.add(item);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListView mListView = findViewById(R.id.userlist_userlist);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, resourceslist);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),resourceslist.get(position),Toast.LENGTH_LONG).show();
                UserStates.INSTANCE.setSelectUsername(resourceslist.get(position));
                Intent intent = new Intent(ListUserPage.this, UserCommentPage.class);
                startActivity(intent);


            }

        });


    }
}