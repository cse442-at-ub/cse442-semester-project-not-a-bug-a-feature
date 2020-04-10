package com.example.tutor4tutor_src;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserCommentPage extends AppCompatActivity {
    boolean isPressed = false;
    String number ="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentpage);
        //final boolean[] isPressed = new boolean[1];
        //isPressed[0] = false;
        final TextView usernameview = (TextView)findViewById(R.id.commentpage_username);
        final ImageButton likebtn = (ImageButton)findViewById(R.id.commentpage_likebtn);
        final TextView numView = (TextView)findViewById(R.id.commentpage_likenumber);
        Button send = (Button)findViewById(R.id.commentpage_sendbtn);
        final EditText inputtext = (EditText) findViewById(R.id.editText13);
        String likeclickedPath = "@drawable/icons8_facebook_like_100";
        String unclickPath = "@drawable/icons8_facebook_like_100__1_";

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

        likebtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int result = Integer.parseInt(numView.getText().toString());

                if (!isPressed) {
                    likebtn.setBackgroundResource(R.drawable.icons8_facebook_like_100__1_);
                    result+=1;

                }else{
                    likebtn.setBackgroundResource(R.drawable.icons8_facebook_like_100);
                    result-=1;
                }
                numView.setText(Integer.toString(result));
                isPressed =!isPressed;
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
