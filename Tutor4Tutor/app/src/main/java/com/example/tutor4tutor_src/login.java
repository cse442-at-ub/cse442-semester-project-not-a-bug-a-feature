package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    SQLiteDatabase db;
    EditText a,b,c;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=openOrCreateDatabase("University",MODE_APPEND,null);
        db.execSQL("create table if not exists Student(id int,name text,course text)");
        a=(EditText)findViewById(R.id.editText1); //TODO: id login params with contextual names ~slgreco
        b=(EditText)findViewById(R.id.editText2);
        c=(EditText)findViewById(R.id.editText3);

    }
    public void SaveData(View v){
        int id=Integer.parseInt(a.getText().toString());
        String name=b.getText().toString();
        String course=c.getText().toString();
        try{
            db.execSQL("Insert into Student values(' "+id+" ' ,'"+name+ " ','"+course+" ')");
            Toast.makeText(getApplicationContext()," Student record Added ", Toast.LENGTH_LONG).show();
            Intent toMenu = new Intent(login.this, MainMenu.class);
            startActivity(toMenu);
        }

        catch(Exception e){
            Toast.makeText(getApplicationContext()," Student record not Added ", Toast.LENGTH_LONG).show();
        }
    }
}

