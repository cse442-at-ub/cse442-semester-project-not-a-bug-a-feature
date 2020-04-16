package com.example.tutor4tutor_src;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu_page extends AppCompatActivity {



    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button10;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);





        button1 = findViewById(R.id.My_Profile);
        button2 = findViewById(R.id.Student);
        button3 = findViewById(R.id.Tutor);
        button4 = findViewById(R.id.Lecture);
        button10=findViewById(R.id.button10);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_page.this, MyProfile.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_page.this, PostsActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_page.this, PostsActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_page.this, ClassesActivity.class);
                startActivity(intent);            }
        });
        button10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_page.this, selectcourse.class);
                startActivity(intent);            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification :
                Toast.makeText(Menu_page.this,"Notification", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Log_out :
                Toast.makeText(Menu_page.this,"Logout",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Contact_us :
                Toast.makeText(Menu_page.this,"Contact us",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}