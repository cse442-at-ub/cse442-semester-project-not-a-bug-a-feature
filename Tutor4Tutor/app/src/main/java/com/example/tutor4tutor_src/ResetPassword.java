package com.example.tutor4tutor_src;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ResetPassword extends AppCompatActivity {
    EditText username, password,newpassword;
    Button button;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        username = (EditText) findViewById(R.id.resetpassword_username);
        password = (EditText) findViewById(R.id.resetpassword_password);
        newpassword = (EditText) findViewById(R.id.resetpassword_newpassword);


        button = (Button) findViewById(R.id.resetpassword_confirm);
        checkBox = (CheckBox) findViewById(R.id.resetpassword_check);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmButtonDown(username.getText().toString(), password.getText().toString(),newpassword.getText().toString()) == 1) {
                    Toast.makeText(getApplicationContext(), "reset!", Toast.LENGTH_LONG).show();
                    if (!(checkBox.isChecked())) {
                        username.setText("");
                        password.setText("");
                        newpassword.setText("");
                    }
                    Intent intent = new Intent(ResetPassword.this, wenxuan_login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "please enter correct details ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    //LOGIN METHOD
    private int confirmButtonDown(String name, String passwd,String newpasswd) {
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

        AsyncTask<String, Integer, Boolean> issucess = new LoginTask().execute("reset", name, passwd,newpasswd);
        try {
            if (!issucess.get()) {
                Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
                return 0;
            } else {

                Toast.makeText(getApplicationContext(), "reset success", Toast.LENGTH_LONG).show();
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
