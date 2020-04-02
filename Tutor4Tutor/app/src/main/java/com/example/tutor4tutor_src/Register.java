package com.example.tutor4tutor_src;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;


public class Register extends AppCompatActivity {
    Button confirm_btn;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        confirm_btn = (Button)findViewById(R.id.register_confirm);
        username = (EditText)findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_password);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<String, Integer, Boolean> issucess = new LoginTask().execute("register", username.getText().toString(), password.getText().toString());
                try {
                    if (!issucess.get())
                    {
                        Toast.makeText(getApplicationContext(), "username exist", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "sign up success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this, wenxuan_login.class);
                        startActivity(intent);
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
