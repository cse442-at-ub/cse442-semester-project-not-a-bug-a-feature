package com.example.tutor4tutor_src;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserCommentPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentpage);
        final TextView usernameview = (TextView)findViewById(R.id.commentpage_username);
        Button send = (Button)findViewById(R.id.commentpage_sendbtn);
        final EditText inputtext = (EditText) findViewById(R.id.editText13);
        usernameview.setText(UserStates.INSTANCE.getSelectUsername());
        showComment();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServerHelper().execute("set",inputtext.getText().toString());
                finish();
                startActivity(getIntent());
            }
        });

    }

    void showComment()
    {

        final ArrayList<String> resourceslist = new ArrayList<String>();
        try {
            AsyncTask<String, Integer, List<? extends String>> str1 = new ServerHelper().execute("getComment",UserStates.INSTANCE.getSelectUsername()+"/comment.txt");
            for (String item : str1.get()) {
                resourceslist.add(item);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListView mListView = findViewById(R.id.commentpage_comments);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, resourceslist);
        mListView.setAdapter(arrayAdapter);

    }

}
