package com.example.tutor4tutor_src;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LecturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);
        //setTitle("Class title: Subject name");

        TextView text = (TextView) findViewById(R.id.tv);
        text.setText("\n Here would be a brief description of this class. \n Here would be a brief description of this class. \n Here would be a brief description of this class. \n");
    }
}
