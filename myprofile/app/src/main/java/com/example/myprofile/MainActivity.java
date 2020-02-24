package com.example.myprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView uName;
    ImageButton uAvail; // ImageButton Object(s) for User Availability Menu, ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String str = "John Doe";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uName = findViewById(R.id.myUsername);
        uName.setText("Juan Do"); // This is a temporary name.

        uAvail = findViewById(R.id.myAvailability);
        uAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadUAvail = new Intent(MainActivity.this, MyAvailability.class);
                startActivity(loadUAvail);
            }
        });
    }
}
