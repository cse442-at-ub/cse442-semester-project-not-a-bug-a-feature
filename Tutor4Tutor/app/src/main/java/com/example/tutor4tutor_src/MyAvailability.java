package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyAvailability extends AppCompatActivity {

    protected ImageButton backButton, updateAvailability;
    protected TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    protected FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_availability);


        monday = findViewById(R.id.viewAvailabilityMonday);
        tuesday = findViewById(R.id.viewAvailabilityTuesday);
        wednesday = findViewById(R.id.viewAvailabilityWednesday);
        thursday = findViewById(R.id.viewAvailabilityThursday);
        friday = findViewById(R.id.viewAvailabilityFriday);
        saturday = findViewById(R.id.viewAvailabilitySaturday);
        sunday = findViewById(R.id.viewAvailabilitySunday);

        backButton = findViewById(R.id.viewProfile);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnUserProfile = new Intent(MyAvailability.this, MyProfile.class);
                startActivity(returnUserProfile);
            }
        });

        updateAvailability = findViewById(R.id.editAvail);
        updateAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popup = new Intent(MyAvailability.this, EditAvailabilityPopup.class);
                startActivity(popup);
            }
        });

        load("abc123");
    }

    protected void load(String uid) {

        try{
            fis = openFileInput(uid.concat("_availability.txt"));

            InputStreamReader streamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String text;

            while((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }

            monday.setText(stringBuilder.toString());
            tuesday.setText(stringBuilder.toString());
            wednesday.setText(stringBuilder.toString());
            thursday.setText(stringBuilder.toString());
            friday.setText(stringBuilder.toString());
            saturday.setText(stringBuilder.toString());
            sunday.setText(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } // Load existing uid_availability.txt
}
