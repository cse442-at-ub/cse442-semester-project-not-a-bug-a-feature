package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {


    TextView uName;
    ImageButton uAvail,toMenu; // ImageButton Object(s) for User Availability Menu, ...

    ImageButton profileupdate; // ImageButton Object(s) for User Availability Menu, ...



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String str = "John Doe";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uName = findViewById(R.id.myUsername);
        uName.setText("Juan Do"); // This is a temporary name.

        uAvail = findViewById(R.id.myAvailability);
        uAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadUAvail = new Intent(MyProfile.this, MyAvailability.class);
                startActivity(loadUAvail);
            }
        });

        toMenu = findViewById(R.id.returnMenu);
        toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMenu = new Intent(MyProfile.this, MainMenu.class);
                startActivity(openMenu);
            }
        });
                profileupdate=(ImageButton) findViewById(R.id.editProfile);
        profileupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfile.this, editpage.class);
                startActivity(intent);
            }
        });
    }
}




