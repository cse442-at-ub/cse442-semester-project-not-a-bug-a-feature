package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class timeslots extends AppCompatActivity {
Button button22, button23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeslots);
        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "saved!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(timeslots.this, MyAvailability.class);
                startActivity(intent);

            }
        });
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Canceled!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(timeslots.this, MyAvailability.class);
                startActivity(intent);

            }
        });


    }
}
