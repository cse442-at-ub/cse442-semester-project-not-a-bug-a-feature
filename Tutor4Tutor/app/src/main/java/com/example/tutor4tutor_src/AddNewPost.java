package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewPost extends AppCompatActivity {

    EditText sub,des;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        sub= (EditText) findViewById(R.id.subjectdetail);
        des= (EditText) findViewById(R.id.descdetail);
        post=findViewById(R.id.Post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.class_button)
                {
                    String text;
                    text = sub.getText().toString() +"\n" +des.getText().toString();
                    Intent intent = getIntent();
                    intent.putExtra("newclass",text);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else
                {
                    finish();
                }
            }
        });
    }
}
