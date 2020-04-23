package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ReplyPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_post);
        final TextView usernameview = (TextView)findViewById(R.id.Username);
        Button rply =(Button)findViewById(R.id.Reply);
        final EditText inputtext = (EditText) findViewById(R.id.Message);

        usernameview.setText(UserStates.INSTANCE.getSelectUsername());
        showReply();

        rply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServerHelper().execute("set",inputtext.getText().toString());
                finish();
                startActivity(getIntent());
            }
        });
    }
    void showReply()
    {

        final ArrayList<String> resourceslist = new ArrayList<String>();
        try {
            AsyncTask<String, Integer, List<? extends String>> str1 = new ServerHelper().execute("getMessage",UserStates.INSTANCE.getSelectUsername()+"/Message.txt");
            for (String item : str1.get()) {
                resourceslist.add(item);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListView mView = findViewById(R.id.listmsg);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, resourceslist);
        mView.setAdapter(arrayAdapter);
    }

}