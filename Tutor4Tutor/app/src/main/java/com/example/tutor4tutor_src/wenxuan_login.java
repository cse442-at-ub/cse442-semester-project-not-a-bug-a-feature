package com.example.tutor4tutor_src;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class wenxuan_login extends AppCompatActivity {

    EditText editText, editText2;
    Button button;
    CheckBox checkBox;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenxuan_login);

        editText = (EditText) findViewById(R.id.wenxuan_editText);
        editText2 = (EditText) findViewById(R.id.wenxuan_editText2);
        button = (Button) findViewById(R.id.wenxuan_button);
        checkBox = (CheckBox) findViewById(R.id.wenxuan_checkBox);
        register = (Button)findViewById(R.id.register_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login(editText2.getText().toString(), editText.getText().toString()) == 1) {
                    Toast.makeText(getApplicationContext(), "logging in!", Toast.LENGTH_LONG).show();
                    if (!(checkBox.isChecked())) {
                        editText2.setText("");
                        editText.setText("");
                    }
                    Intent intent = new Intent(wenxuan_login.this, wenxuan_ProfilePage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "please enter correct details ", Toast.LENGTH_LONG).show();

                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wenxuan_login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    //LOGIN METHOD
    private int login(String name, String passwd) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        if (name.isEmpty() || passwd.isEmpty()) {
//            map.put(1,"asdasd");
//            map.put(2,"pppppp");
//            String s = JSONParser.INSTANCE.dic2json(map);
//d c
//            Map<Integer,String> dic = JSONParser.INSTANCE.json2dic(s);
//
//            Toast.makeText(getApplicationContext(), dic.get(3), Toast.LENGTH_LONG).show();
            return 0;
        }

        AsyncTask<String, Integer, Boolean> issucess = new LoginTask().execute("login", name, passwd);
        try {
            if (!issucess.get()) {
                Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
                return 0;
            } else {

                Toast.makeText(getApplicationContext(), "sign up success", Toast.LENGTH_LONG).show();
                return 1;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;

    }
}
