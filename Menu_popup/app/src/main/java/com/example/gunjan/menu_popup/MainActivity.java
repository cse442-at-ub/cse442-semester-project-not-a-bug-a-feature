package com.example.gunjan.menu_popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.My_Profile);
        button2 = findViewById(R.id.Student);
        button3 = findViewById(R.id.Tutor);
        button4 = findViewById(R.id.Lecture);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMyProfile();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                OpenFindStudent();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                OpenFindTutor();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                OpenTakeLecture();
            }
        });
        }

        private void OpenMyProfile(){
            Intent intent = new Intent(MainActivity.this, My_Profile.class);
            startActivity(intent);
        }
    private void OpenFindStudent(){
        Intent intent = new Intent(MainActivity.this, FindStudent.class);
        startActivity(intent);
    }
    private void OpenFindTutor(){
        Intent intent = new Intent(MainActivity.this, FindTutor.class);
        startActivity(intent);
    }
    private void OpenTakeLecture(){
        Intent intent = new Intent(MainActivity.this, TakeLecture.class);
        startActivity(intent);
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
                Toast.makeText(MainActivity.this,"Notification", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Log_out :
                Toast.makeText(MainActivity.this,"Logout",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Contact_us :
                Toast.makeText(MainActivity.this,"Contact us",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
