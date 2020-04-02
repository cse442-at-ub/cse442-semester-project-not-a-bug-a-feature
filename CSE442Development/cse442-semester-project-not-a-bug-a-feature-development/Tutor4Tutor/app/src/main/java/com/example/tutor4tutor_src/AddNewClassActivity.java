package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNewClassActivity extends AppCompatActivity {

    EditText title, subject;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_class);

        title = (EditText)findViewById(R.id.class_title);
        subject = (EditText)findViewById(R.id.class_subject);

        confirm = (Button)findViewById(R.id.class_button);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.class_button)
                {
                    String text;
                    text = title.getText().toString() +"\n" +subject.getText().toString();
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
