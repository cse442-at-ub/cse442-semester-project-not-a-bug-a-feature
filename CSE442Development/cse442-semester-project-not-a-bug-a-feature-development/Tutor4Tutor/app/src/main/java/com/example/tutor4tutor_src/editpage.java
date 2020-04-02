package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class editpage extends AppCompatActivity {
    Button button4, button5, button8;
    TextView textView8,textView6,textView9,textView7,textView10,textView11,textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpage);

        textView4 = (TextView) findViewById(R.id.textView4);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10= (TextView) findViewById(R.id.textView10);
        textView11= (TextView) findViewById(R.id.textView11);
        button4= (Button) findViewById(R.id.button4);
        button5= (Button) findViewById(R.id.button5);
        button8= (Button) findViewById(R.id.button8);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editpage.this, experience.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editpage.this, highschoolcollege.class);
                startActivity(intent);

            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saving Data!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(editpage.this, MyProfile.class);
                startActivity(intent);

            }
        });




    }
}