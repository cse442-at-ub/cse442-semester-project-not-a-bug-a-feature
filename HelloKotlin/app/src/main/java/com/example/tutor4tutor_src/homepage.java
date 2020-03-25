package com.example.tutor4tutor_src;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class homepage extends AppCompatActivity {



    EditText textView5, text;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // the input form is "set/get/remove","filename","username","id","data"
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("Key = ClassTitle","value = yourinputvalue");
        hm.put("Key = ClassSubject","value = yourinputvalue");
        String jsonstring = new JSONObject(hm).toString();
//        new ClassInfoTask().execute("set","classinfo.txt","yang","10001",jsonstring);
//        new ClassInfoTask().execute("set","classinfo.txt","yang","10002",jsonstring);
//        new ClassInfoTask().execute("set","classinfo.txt","yang","10003",jsonstring);

        new ClassInfoTask().execute("remove","classinfo.txt","yang","10002");

        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, login.class);
                startActivity(intent);

            }
        });


    }
}







