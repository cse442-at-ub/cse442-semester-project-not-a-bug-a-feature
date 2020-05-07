package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNewClassActivity extends AppCompatActivity {

    EditText title;
    Button confirm;
    RadioGroup subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_class);

        title = (EditText)findViewById(R.id.class_title);
        subject = (RadioGroup) findViewById(R.id.radio_subject);

        confirm = (Button)findViewById(R.id.class_button);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.class_button)
                {
                    int id = subject.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton) findViewById(id);
                    String text;
                    text = title.getText().toString() +'\n'+ rb.getText().toString() +'\n' + "Wenxuan Wang";
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
