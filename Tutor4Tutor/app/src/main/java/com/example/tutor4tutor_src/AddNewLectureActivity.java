package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class AddNewLectureActivity extends AppCompatActivity {

//    Video newlec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lecture);



        Button confirm = (Button)findViewById(R.id.class_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "lecture video will be added on lecture list", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                EditText t = (EditText) findViewById(R.id.class_title);
                EditText u = (EditText) findViewById(R.id.lecture_url);
                //      newlec = new Video(t.getText().toString(), u.getText().toString());
                String tnu = t.getText().toString() + '\n' + u.getText().toString();

                Intent intent = getIntent();
                intent.putExtra("newlec", tnu);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
