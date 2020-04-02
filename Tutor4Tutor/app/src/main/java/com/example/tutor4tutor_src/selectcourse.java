package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class selectcourse extends AppCompatActivity {
Button button11;
    Button button12;
    Button button13;
    Button button14;
    ImageButton imagebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcourse);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        imagebutton=findViewById(R.id.imageButton);

        imagebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectcourse.this, Menu_page.class);
                startActivity(intent);            }
        });

        button11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectcourse.this, progress.class);
                startActivity(intent);            }
        });

        button12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectcourse.this, progress2.class);
                startActivity(intent);            }
        });

        button13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectcourse.this, progress3.class);
                startActivity(intent);            }
        });

        button14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectcourse.this, progress4.class);
                startActivity(intent);            }
        });


    }
}
