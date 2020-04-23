package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class forgetpassword extends AppCompatActivity {
Button button16, button17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        button16= (Button) findViewById(R.id.button16);
        button17= (Button) findViewById(R.id.button17);

 button17.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent intemt = new Intent(forgetpassword.this, security.class);
         startActivity(intemt);
     }
 });

        }

    }

