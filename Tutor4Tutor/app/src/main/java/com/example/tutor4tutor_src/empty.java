package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class empty extends AppCompatActivity {
    Button button22, button23;
    CheckBox checkBox6, checkBox7, checkBox8,checkBox9, checkBox10, checkBox11,checkBox12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        checkBox7= (CheckBox) findViewById(R.id.checkBox7);
        checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
        checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
        checkBox10 = (CheckBox) findViewById(R.id.checkBox10);
        checkBox11 = (CheckBox) findViewById(R.id.checkBox11);
        checkBox12 = (CheckBox) findViewById(R.id.checkBox12);


        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "saved!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(empty.this, MyAvailability.class);
                startActivity(intent);

            }
        });
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Canceled!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(empty.this, MyAvailability.class);
                startActivity(intent);

            }
        });
    }
}
