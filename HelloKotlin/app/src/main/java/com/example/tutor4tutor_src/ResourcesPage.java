package com.example.tutor4tutor_src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ResourcesPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        Button uploadbtn = (Button)findViewById(R.id.resources_upload_btn);
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMenu = new Intent(ResourcesPage.this, UploadFilePage.class);
                startActivity(openMenu);
            }
        });

        String resources = null;
        try {
            resources = new ServerFileHelper().execute("get", UserStates.INSTANCE.getUsername()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> resourceslist =  new ArrayList<String>();
        for (String item  : resources.split(","))
        {
            resourceslist.add(item);
        }
        ListView mListView = findViewById(R.id.resources_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, resourceslist);
        mListView.setAdapter(arrayAdapter);

//        mListView.setOnItemClickListener(); = object : AdapterView.OnItemClickListener {
//            override fun onItemClick(parent: AdapterView<*>, view: View,
//                    position: Int, id: Long) {
//                val itemValue = mListView.getItemAtPosition(position) as String
//                UserSingleton.targetuserid = itemValue
//
//                // Toast the values
//                Toast.makeText(applicationContext,
//                        "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG)
//                        .show()
//                // start your next activity
//                startActivity(intent)
//            }
//        }
    }

}
