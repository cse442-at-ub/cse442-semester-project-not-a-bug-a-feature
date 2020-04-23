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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListUserPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        Button add = (Button) findViewById(R.id.userliset_addbtn);
        final Button whitelist= (Button)findViewById(R.id.userlist_whitelist);
        AsyncTask<String, Integer, List<? extends String>> str1 = new ServerHelper().execute("get","userstatus.txt");
        final ArrayList<String> resourceslist = new ArrayList<String>();
        final ArrayList<Boolean> isSelected = new ArrayList<Boolean>();
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

                isSelected.set(position,!isSelected.get(position));
                if (isSelected.get(position))
                    mListView.getChildAt(position).setBackgroundColor(Color.RED);
                else
                    mListView.getChildAt(position).setBackgroundColor(Color.WHITE);
                String str="";

                for(int i=0;i<sp.size();i++)
                {
                    str+= resourceslist.get(sp.keyAt(i)) +",";


                }
                Toast.makeText(getApplicationContext(), ""+str, Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(ListUserPage.this, UserCommentPage.class);
                //startActivity(intent);
            }

        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        whitelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}