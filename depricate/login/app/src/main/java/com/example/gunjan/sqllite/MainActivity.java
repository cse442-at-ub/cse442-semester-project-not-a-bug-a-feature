package com.example.gunjan.sqllite;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText a,b,c;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("University",MODE_APPEND,null);
        db.execSQL("create table if not exists Student(id int,name text,course text)");
        a=(EditText)findViewById(R.id.editText1);
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

        }

        catch(Exception e){
            Toast.makeText(getApplicationContext()," Student record not Added ", Toast.LENGTH_LONG).show();
        }
    }
}
