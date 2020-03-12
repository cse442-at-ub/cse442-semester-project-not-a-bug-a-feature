package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    ImageButton toMyProfile;
    Button toFindStudent, toFindTutor, toFindLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toMyProfile = findViewById(R.id.menuButtonMyProfile);
        toMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openProfile = new Intent(MainMenu.this, MyProfile.class);
                startActivity(openProfile);
            }
        });

        toFindStudent = findViewById(R.id.menuButtonFindStudent);
        toFindStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openProfile = new Intent(MainMenu.this, PostsActivity.class);
                startActivity(openProfile);
            }
        });

        toFindTutor = findViewById(R.id.menuButtonFindTutor);
        toFindTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openProfile = new Intent(MainMenu.this, PostsActivity.class);
                startActivity(openProfile);
            }
        });

        toFindLecture = findViewById(R.id.menuButtonFindClass);
        toFindLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openProfile = new Intent(MainMenu.this, ClassesActivity.class);
                startActivity(openProfile);
            }
        });


    }



}
