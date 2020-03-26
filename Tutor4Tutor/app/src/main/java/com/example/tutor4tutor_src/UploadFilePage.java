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

public class UploadFilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadfile);
        final EditText filename,content;
        Button confirm;
        filename = (EditText)findViewById(R.id.uploadfile_filename);
        content =  (EditText)findViewById(R.id.uploadfile_content);
        confirm = (Button)findViewById(R.id.uploadfile_confirmbtn);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =UserStates.INSTANCE.getUsername()+"_"+ filename.getText().append(".txt").toString();
                // the input form is "set/get/remove","filename","username","id","data"
                Map<String, String> hm = new HashMap<String, String>();
                hm.put("content",content.getText().toString());
                String jsonstring = new JSONObject(hm).toString();
                new ClassInfoTask().execute("set",name,UserStates.INSTANCE.getUsername(),"0",jsonstring);
                Intent openMenu = new Intent(UploadFilePage.this, ResourcesPage.class);
                startActivity(openMenu);
            }
        });


    }
}
