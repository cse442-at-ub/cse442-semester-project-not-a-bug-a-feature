package com.example.tutor4tutor_src;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;




public class login extends AppCompatActivity {

    EditText editText, editText2;
    Button button;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login(editText2.getText().toString(), editText.getText().toString()) == 1) {
                    Toast.makeText(getApplicationContext(), "logging in!", Toast.LENGTH_LONG).show();
                    if (!(checkBox.isChecked())) {
                        editText2.setText("");
                        editText.setText("");

                    }

                    Intent intent = new Intent(login.this, Menu_page.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "please enter correct details ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    //LOGIN METHOD
    private int login(String name , String passwd)
    {
        if(name.isEmpty() || passwd.isEmpty())
        {
            return 0;
        }
        else if (name.equals("testuser") && passwd.equals("password"))
        {
            return 1;
        }
        return 0;
    }};





