package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class AddNewLectureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lecture);

        Button confirm = (Button)findViewById(R.id.class_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "lecture video will be added on lecture list", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
