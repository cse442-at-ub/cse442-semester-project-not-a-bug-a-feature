package com.example.myprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MyAvailability extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_availability);
        backButton = findViewById(R.id.viewProfile);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnUserProfile = new Intent(MyAvailability.this, MainActivity.class);
                startActivity(returnUserProfile);
            }
        });
    }
}
